package kr.co.kurly.remote.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kr.co.kurly.common.model.CommonException
import kr.co.kurly.mockserver.MockInterceptor
import org.json.JSONObject
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context
    ): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                config {
                    addInterceptor(MockInterceptor(context))
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                    explicitNulls = false
                })
            }

            install(Logging) {
                level = LogLevel.ALL

                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("NetworkModule: Logger").d(message)
                    }
                }
            }

            install(HttpTimeout) {
                connectTimeoutMillis = CONNECTION_TIMEOUT
                requestTimeoutMillis = CONNECTION_TIMEOUT
                socketTimeoutMillis = CONNECTION_TIMEOUT
            }

            install(DefaultRequest) {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    Timber.tag("HttpResponseValidator").e(cause)

                    when (cause) {
                        is UnknownHostException -> throw CommonException(message = "네트워크 연결상태를 확인해주세요")
                        is HttpRequestTimeoutException -> throw CommonException(message = "네트워크 오류가 발생하였습니다.")
                    }
                }

                validateResponse { response ->
                    if (!response.status.isSuccess()) {
                        val errorCode = runCatching {
                            JSONObject(response.bodyAsText()).getInt(RESPONSE_ERROR_CODE)
                        }.getOrNull()

                        val errorMessage = runCatching {
                            JSONObject(response.bodyAsText()).getString(RESPONSE_ERROR_MESSAGE)
                        }.getOrNull()

                        Timber.tag("HttpResponseValidator").e("Message:$errorMessage Code:$errorCode")

                        throw CommonException(message = errorMessage, code = errorCode)
                    }
                }
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://kurly.com/"
        private const val RESPONSE_ERROR_MESSAGE = "message"
        private const val RESPONSE_ERROR_CODE = "code"

        private const val CONNECTION_TIMEOUT = 10_000L
    }
}