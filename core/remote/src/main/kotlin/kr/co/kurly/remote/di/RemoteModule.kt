package kr.co.kurly.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kr.co.kurly.data.source.remote.ProductRemoteDataSource
import kr.co.kurly.remote.ProductRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RemoteModule {
    @Singleton
    @Provides
    fun provideProductRemoteDataSource(
        client: HttpClient
    ): ProductRemoteDataSource = ProductRemoteDataSourceImpl(client)
}