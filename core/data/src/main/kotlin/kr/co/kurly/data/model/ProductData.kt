package kr.co.kurly.data.model

data class ProductData(
    val discountedPrice: Int?,
    val id: Long,
    val image: String,
    val isSoldOut: Boolean,
    val name: String,
    val originalPrice: Int
)