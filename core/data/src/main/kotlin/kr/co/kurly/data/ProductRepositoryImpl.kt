package kr.co.kurly.data

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kr.co.kurly.data.mapper.ProductSectionDataMapper
import kr.co.kurly.data.source.local.ProductLocalDataSource
import kr.co.kurly.data.source.remote.ProductRemoteDataSource
import kr.co.kurly.domain.ProductRepository
import kr.co.kurly.domain.model.ProductSection
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val local: ProductLocalDataSource,
    private val remote: ProductRemoteDataSource
) : ProductRepository {
    private val mutex = Mutex()

    private val _notification: MutableSharedFlow<Unit> = MutableSharedFlow()
    override val notification: SharedFlow<Unit> = _notification.asSharedFlow()

    private val products = mutableListOf<ProductSection>()
    private var nextPage: Int = 1
    private var hasMore: Boolean = true

    private fun receiveNotification(): Flow<Unit> {
        return notification
            .onStart { if (products.isEmpty()) emit(Unit) }
            .onEach {
                mutex.withLock {
                    if (!hasMore) return@onEach

                    val section = remote.fetchSection(nextPage)
                    if (section.paging?.nextPage != null) {
                        hasMore = true
                        nextPage = section.paging.nextPage
                    } else {
                        hasMore = false
                    }

                    coroutineScope {
                        section.data.map { data ->
                            async {
                                val products = remote.fetchProduct(data.id)
                                ProductSectionDataMapper.mapToRight(data to products)
                            }
                        }.awaitAll()
                    }.let { products.addAll(it) }
                }
            }.buffer()
    }

    override fun fetchLikedIds(): Flow<Set<Long>> {
        return local.fetchLikedIds()
    }

    override suspend fun updateLiked(id: Long, isLiked: Boolean) {
        if (isLiked) {
            local.deleteLikedId(id)
        } else {
            local.saveLikedId(id)
        }
    }

    override fun fetchProducts(): Flow<Pair<Boolean, List<ProductSection>>> {
        return receiveNotification().mapLatest { hasMore to products.toList() }
    }

    override suspend fun load(refresh: Boolean) {
        mutex.withLock {
            if (refresh) {
                nextPage = 1
                hasMore = true
                products.clear()
            }

            if (hasMore) {
                _notification.emit(Unit)
            }
        }
    }
}