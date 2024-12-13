package com.capstone.agriclime.network

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("data") val data: WeatherData?,
    @SerializedName("weather") val weather: String?
)

data class WeatherData(
    @SerializedName("temp") val temp: String?,
    @SerializedName("wind_speed") val wind_speed: String?,
    @SerializedName("wind_degree") val wind_degree: String?,
    @SerializedName("humidity") val humidity: String?,
    @SerializedName("uv") val uv: String?
)



