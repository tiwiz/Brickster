package com.rob.legopedia.domain.network

import com.rob.legopedia.domain.network.TokenInterceptor.Companion.API_KEY
import retrofit2.http.*

interface RebrickableService {

    @Headers("${API_KEY}: true")
    @GET("/api/v3/lego/sets/")
    suspend fun searchSet(@Query("search") searchKey: String) : ResponseDTO
}