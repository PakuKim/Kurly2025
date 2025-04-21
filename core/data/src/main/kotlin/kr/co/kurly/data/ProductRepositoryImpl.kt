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
    private val _notification: MutableSharedFlow<Unit> = MutableSharedFlow()
    override val notification: SharedFlow<Unit> = _notification.asSharedFlow()

    private val products = mutableListOf<ProductSection>()
    private var currentPage: Int = 1

    private fun receiveNotification(): Flow<Unit> {
        return notification
            .onStart { emit(Unit) }
            .onEach {
                val section = remote.fetchSection(currentPage)
                currentPage = section.paging?.nextPage ?: 0

                coroutineScope {
                    section.data.map { data ->
                        async {
                            val products = remote.fetchProduct(data.id)
                            ProductSectionDataMapper.mapToRight(data to products)
                        }
                    }.awaitAll()
                }.let { products.addAll(it) }
            }.buffer()
    }

    override fun fetchLikedIds(): Flow<Set<Long>> {
        return local.fetchLikedIds()
    }

    override fun fetchProducts(): Flow<List<ProductSection>> {
        return receiveNotification().mapLatest { products }
    }

    override suspend fun load(page: Int?) {
        page?.let { currentPage = it }
        if (currentPage > 0) _notification.emit(Unit)
    }
}