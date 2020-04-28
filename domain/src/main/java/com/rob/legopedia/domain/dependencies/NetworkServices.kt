package com.rob.legopedia.domain.dependencies

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rob.legopedia.domain.network.LegoService
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

@Suppress("EXPERIMENTAL_API_USAGE")
@Module
class NetworkModule {

    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(BODY)
    }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Named("LegoUrl")
    fun provideRebrickableUrl() = BASE_URL

    @Provides
    fun provideJsonConfiguration() =
        JsonConfiguration.Stable.copy(ignoreUnknownKeys = true)

    @Provides
    fun provideJsonConverter(jsonConfiguration: JsonConfiguration) =
        Json(jsonConfiguration).asConverterFactory(JSON.toMediaType())

    @Provides
    fun rebrickableService(
        client: OkHttpClient,
        @Named("LegoUrl") baseUrl: String,
        jsonConverter: Converter.Factory
    ): LegoService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(jsonConverter)
            .build()
            .create(LegoService::class.java)

    companion object {
        private const val JSON = "application/json"
        private const val BASE_URL = "https://www.lego.com/"
    }
}