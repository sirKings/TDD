package com.kings.tdd.data.remote

import com.kings.tdd.BuildConfig
import com.kings.tdd.data.remote.response.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ShoppingApi {

    @GET("/api/")
    suspend fun searchImages(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") searchQuery: String
    ): Response<ImageResponse>

}