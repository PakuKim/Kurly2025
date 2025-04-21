package kr.co.kurly.domain.model

data class Product(
    val id: Long,
    val image: String,
    val name: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val saleRate: Int?,
    val isSoldOut: Boolean
)