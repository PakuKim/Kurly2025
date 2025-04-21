package kr.co.kurly.remote.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class GetProductListResponse(
    @SerialName("data")
    val `data`: List<Data>
) {
    @Serializable
    data class Data(
        @SerialName("discountedPrice")
        val discountedPrice: Int?,
        @SerialName("id")
        val id: Long,
        @SerialName("image")
        val image: String,
        @SerialName("isSoldOut")
        val isSoldOut: Boolean,
        @SerialName("name")
        val name: String,
        @SerialName("originalPrice")
        val originalPrice: Int
    )
}