package kr.co.kurly.domain.interactor

import kr.co.kurly.domain.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke() = repository.fetchProducts()
}