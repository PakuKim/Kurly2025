package kr.co.kurly.domain.interactor

import kr.co.kurly.domain.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateProductLikedUseCase @Inject constructor(
    private val repository: ProductRepository
){
    suspend operator fun invoke(id: Long, isLiked: Boolean) = repository.updateLiked(id, isLiked)
}