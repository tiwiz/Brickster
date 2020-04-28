package com.rob.legopedia.domain.network

import retrofit2.http.GET
import retrofit2.http.Query

interface LegoService {

    @GET("/service/biservice/search")
    suspend fun searchSet(@Query("prefixText") search: String) : LegoResponseDTO
}