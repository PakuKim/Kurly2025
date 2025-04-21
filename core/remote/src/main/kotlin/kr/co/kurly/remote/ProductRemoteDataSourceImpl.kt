package kr.co.kurly.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.InternalSerializationApi
import kr.co.kurly.data.model.ProductData
import kr.co.kurly.data.model.ProductSectionData
import kr.co.kurly.data.source.remote.ProductRemoteDataSource
import kr.co.kurly.remote.mapper.ProductRemoteMapper
import kr.co.kurly.remote.mapper.ProductSectionRemoteMapper
import kr.co.kurly.remote.model.GetProductListResponse
import kr.co.kurly.remote.model.GetProductSectionResponse
import javax.inject.Inject

internal class ProductRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : ProductRemoteDataSource {
    companion object {
        private const val SECTION = "sections"
        private const val SECTION_PRODUCT = "section/products"
    }

    @OptIn(InternalSerializationApi::class)
    override suspend fun fetchSection(page: Int): ProductSectionData {
        return client.get(SECTION) {
            parameter("page", page)
        }.body<GetProductSectionResponse>().let(ProductSectionRemoteMapper::mapToRight)
    }

    @OptIn(InternalSerializationApi::class)
    override suspend fun fetchProduct(id: Int): List<ProductData> {
        return client.get(SECTION_PRODUCT) {
            parameter("sectionId", id)
        }.body<GetProductListResponse>().data.map(ProductRemoteMapper::mapToRight)
    }
}