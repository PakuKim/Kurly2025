package kr.co.kurly.domain.interactor

import kr.co.kurly.domain.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadProductUseCase @Inject constructor(
    private val repository: ProductRepository
){
    suspend operator fun invoke(page: Int? = null) = repository.load(page)
}