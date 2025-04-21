package kr.co.kurly.remote.mapper

import kotlinx.serialization.InternalSerializationApi
import kr.co.kurly.common.util.Mapper
import kr.co.kurly.data.model.ProductSectionData
import kr.co.kurly.remote.model.GetProductSectionResponse

@OptIn(InternalSerializationApi::class)
internal object ProductSectionRemoteMapper: Mapper<GetProductSectionResponse, ProductSectionData> {
    override fun mapToRight(from: GetProductSectionResponse): ProductSectionData {
        return from.let { product ->
            ProductSectionData(
                data = product.data.map { data ->
                    ProductSectionData.Data(
                        id = data.id,
                        title = data.title,
                        type = data.type,
                        url = data.url
                    )
                },
                paging = product.paging?.let { paging ->
                    ProductSectionData.Paging(
                        nextPage = paging.nextPage
                    )
                }
            )
        }
    }
}