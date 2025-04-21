package kr.co.kurly.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kr.co.kurly.domain.model.ProductSection

interface ProductRepository {
    val notification: SharedFlow<Unit>

    fun fetchLikedIds(): Flow<Set<Long>>

    fun fetchProducts(): Flow<List<ProductSection>>

    suspend fun load(page: Int?)
}