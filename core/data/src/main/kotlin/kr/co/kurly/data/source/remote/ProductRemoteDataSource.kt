package kr.co.kurly.data.source.remote

import kr.co.kurly.data.model.ProductData
import kr.co.kurly.data.model.ProductSectionData

interface ProductRemoteDataSource {
    suspend fun fetchSection(page: Int): ProductSectionData

    suspend fun fetchProduct(id: Int): List<ProductData>
}