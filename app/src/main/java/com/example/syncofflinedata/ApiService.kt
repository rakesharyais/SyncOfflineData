package com.example.syncofflinedata

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("Sync")
    suspend fun syncData(@Body data: List<DataEntity>): Response<Unit>


}