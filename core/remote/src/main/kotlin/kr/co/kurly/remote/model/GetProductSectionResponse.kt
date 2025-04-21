package kr.co.kurly.remote.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class GetProductSectionResponse(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("paging")
    val paging: Paging?
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("type")
        val type: String,
        @SerialName("url")
        val url: String
    )

    @Serializable
    data class Paging(
        @SerialName("next_page")
        val nextPage: Int
    )
}