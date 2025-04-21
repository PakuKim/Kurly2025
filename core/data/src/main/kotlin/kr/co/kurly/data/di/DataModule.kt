package kr.co.kurly.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.kurly.data.ProductRepositoryImpl
import kr.co.kurly.domain.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Singleton
    @Binds
    fun bindProductRepository(
        repository: ProductRepositoryImpl
    ): ProductRepository
}