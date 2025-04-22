package kr.co.kurly.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductSection(
    val id: Int,
    val title: String,
    val type: ProductType,
    val products: List<Product>
): Parcelable