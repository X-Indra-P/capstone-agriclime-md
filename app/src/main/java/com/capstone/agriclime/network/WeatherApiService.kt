package com.capstone.agriclime.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WeatherApiService {
    @POST("cuaca")
    fun getWeather(@Body request: CityRequest): Call<WeatherResponse>
}

