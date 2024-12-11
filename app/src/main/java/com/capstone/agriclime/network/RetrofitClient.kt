package com.capstone.agriclime.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://backend-dot-capstone-443615.et.r.appspot.com/"

    val instance: PlantApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlantApiService::class.java)
    }
}