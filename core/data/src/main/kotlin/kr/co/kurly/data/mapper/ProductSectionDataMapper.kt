package kr.co.kurly.data.mapper

import kr.co.kurly.common.util.Mapper
import kr.co.kurly.data.model.ProductData
import kr.co.kurly.data.model.ProductSectionData
import kr.co.kurly.domain.model.Product
import kr.co.kurly.domain.model.ProductSection

internal object ProductSectionDataMapper : Mapper<Pair<ProductSectionData.Data, List<ProductData>>, ProductSection> {
    override fun mapToRight(from: Pair<ProductSectionData.Data, List<ProductData>>): ProductSection {
        val (section, products) = from
        return ProductSection(
            id = section.id,
            title = section.title,
            type = enumValueOf(section.type.uppercase()),
            products = products.map { product ->
                Product(
                    id = product.id,
                    image = product.image,
                    name = product.name,
                    originalPrice = product.originalPrice,
                    disCountedPrice = product.disCountedPrice,
                    saleRate = product.disCountedPrice?.let { price ->
                        ((product.originalPrice - price) / product.originalPrice.toFloat() * 100).toInt()
                    },
                    isSoldOut = product.isSoldOut
                )
            }
        )
    }
}