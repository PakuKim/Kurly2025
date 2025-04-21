package kr.co.kurly.domain.model

data class ProductSection(
    val id: Int,
    val title: String,
    val type: ProductType,
    val products: List<Product>
)