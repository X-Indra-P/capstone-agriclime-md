package com.capstone.agriclime.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient3 {
    private const val BASE_URL = "https://backend-dot-capstone-443615.et.r.appspot.com/"

    val instance: ApiService2 by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService2::class.java)
    }
}

