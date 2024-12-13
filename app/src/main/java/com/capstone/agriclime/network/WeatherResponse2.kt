package com.capstone.agriclime.network

data class WeatherResponse2(
    val data: List<WeatherData2>,
    val weather: List<String>
)

data class WeatherData2(
    val cloud: String?,
    val humidity: String?,
    val precip: String?,
    val pressure: String?,
    val temp: String?,
    val time: String?,
    val uv: String?,
    val wind_degree: String?,
    val wind_speed: String?
)

