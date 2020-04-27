package com.rob.legopedia.domain.dependencies

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rob.legopedia.domain.network.RebrickableService
import com.rob.legopedia.domain.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(BODY)
    }

    @Provides
    fun tokenInterceptor() = TokenInterceptor()

    @Provides
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()

    @Provides
    @Named("RebrickableUrl")
    fun provideRebrickableUrl() = BASE_URL

    @Provides
    fun rebrickableService(client: OkHttpClient, @Named("RebrickableUrl") baseUrl: String): RebrickableService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(JSON.toMediaType()))
            .build()
            .create(RebrickableService::class.java)

    companion object {
        private const val JSON = "application/json"
        private const val BASE_URL = "https://rebrickable.com/"
    }
}