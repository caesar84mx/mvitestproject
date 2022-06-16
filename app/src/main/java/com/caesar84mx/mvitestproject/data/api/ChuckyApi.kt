package com.caesar84mx.mvitestproject.data.api

import com.caesar84mx.mvitestproject.data.model.FactResponse
import retrofit2.http.GET

interface ChuckyApi {
    @GET("jokes/random")
    suspend fun getRandomFact(): FactResponse
}