package kr.co.kurly.remote.mapper

import kotlinx.serialization.InternalSerializationApi
import kr.co.kurly.common.util.Mapper
import kr.co.kurly.data.model.ProductData
import kr.co.kurly.remote.model.GetProductListResponse

@OptIn(InternalSerializationApi::class)
internal object ProductRemoteMapper: Mapper<GetProductListResponse.Data, ProductData> {
    override fun mapToRight(from: GetProductListResponse.Data): ProductData {
        return from.let { product ->
            ProductData(
                discountedPrice = product.discountedPrice,
                id = product.id,
                image = product.image,
                isSoldOut = product.isSoldOut,
                name = product.name,
                originalPrice = product.originalPrice
            )
        }
    }
}