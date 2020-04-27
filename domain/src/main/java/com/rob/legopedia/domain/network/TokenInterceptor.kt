package com.rob.legopedia.domain.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request =
            if (chain.request().header(API_KEY) != null) {
                chain.request().newBuilder()
                    .removeHeader(API_KEY)
                    .addHeader(HEADER_NAME, HEADER_VALUE)
                    .build()
            } else {
                chain.request()
            }

        return chain.proceed(request)
    }

    companion object {
        const val API_KEY = "withAPIKey"
        private const val HEADER_NAME = "Authorization"
        private const val HEADER_VALUE = "key c22bcd78249a356205667b8a1777932c" //TODO remove
    }
}