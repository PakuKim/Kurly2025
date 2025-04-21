package kr.co.kurly.data.model

data class ProductSectionData(
    val `data`: List<Data>,
    val paging: Paging?
) {
    data class Data(
        val id: Int,
        val title: String,
        val type: String,
        val url: String
    )

    data class Paging(
        val nextPage: Int
    )
}