package com.capstone.agriclime.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService2 {
    @POST("cuaca/future")
    fun getHoursly(@Body request: CityRequest2): Call<WeatherResponse2>
}
