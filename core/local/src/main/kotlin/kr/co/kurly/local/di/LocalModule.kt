package kr.co.kurly.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.kurly.data.source.local.ProductLocalDataSource
import kr.co.kurly.local.ProductLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LocalModule {
    @Singleton
    @Binds
    fun bindProductLocalDataSource(
        dataSource: ProductLocalDataSourceImpl
    ): ProductLocalDataSource
}