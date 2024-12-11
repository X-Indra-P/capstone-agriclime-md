package com.capstone.agriclime.network

import retrofit2.Call
import retrofit2.http.GET

interface PlantApiService {
    @GET("ListAll") // Ganti dengan endpoint yang benar
    fun getPlants(): Call<PlantResponse>
}