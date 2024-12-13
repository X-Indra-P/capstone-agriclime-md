package com.capstone.agriclime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.capstone.agriclime.R
import com.capstone.agriclime.network.CityRequest
import com.capstone.agriclime.network.CityRequest2
import com.capstone.agriclime.network.RetrofitClient2
import com.capstone.agriclime.network.RetrofitClient3
import com.capstone.agriclime.network.WeatherResponse
import com.capstone.agriclime.network.WeatherResponse2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvTemperature: TextView
    private lateinit var tvWeatherDescription: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var tvArahAngin: TextView
    private lateinit var humidityPercent: TextView
    private lateinit var uvIndexValue: TextView
    private lateinit var progressBar: ProgressBar

    // Hourly Weather Views
    private lateinit var tvJamSekarang: TextView
    private lateinit var tvJam1: TextView
    private lateinit var tvJam2: TextView
    private lateinit var tvJam3: TextView
    private lateinit var tvTempSekarang: TextView
    private lateinit var tvTempJam1: TextView
    private lateinit var tvTempJam2: TextView
    private lateinit var tvTempJam3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Bind Views
        tvTemperature = findViewById(R.id.tvTemperature)
        tvWeatherDescription = findViewById(R.id.tvWeatherDescription)
        tvWindSpeed = findViewById(R.id.tvWindSpeed)
        tvArahAngin = findViewById(R.id.tvArahAngin)
        humidityPercent = findViewById(R.id.humidityPercent)
        uvIndexValue = findViewById(R.id.uvIndexValue)
        progressBar = findViewById(R.id.progress_bar)

        // Bind Hourly Weather Views
        tvJamSekarang = findViewById(R.id.tvJamSekarang)
        tvJam1 = findViewById(R.id.tvJam1)
        tvJam2 = findViewById(R.id.tvJam2)
        tvJam3 = findViewById(R.id.tvJam3)
        tvTempSekarang = findViewById(R.id.tvTempSekarang)
        tvTempJam1 = findViewById(R.id.tvTempJam1)
        tvTempJam2 = findViewById(R.id.tvTempJam2)
        tvTempJam3 = findViewById(R.id.tvTempJam3)

        // Call APIs
        fetchWeatherPrediction("samarinda", getCurrentTime())
        fetchWeatherData("samarinda")

        // Bind News Icon
        val newsIcon = findViewById<ImageButton>(R.id.newsIcon)
        newsIcon.setOnClickListener {
            val intent = Intent(this, PlantActivity::class.java)
            startActivity(intent)
        }

        // Bind Current Weather Card
        val currentWeatherCard = findViewById<CardView>(R.id.currentWeatherCard)
        currentWeatherCard.setOnClickListener {
            val intent = Intent(this, CuacaActivity::class.java)
            startActivity(intent)
        }

        val warningCard = findViewById<CardView>(R.id.warningCard)
        warningCard.setOnClickListener {
            val intent = Intent(this, PeringatanActivity::class.java)
            startActivity(intent)
        }

        val humidity = findViewById<CardView>(R.id.humiditycardview)
        humidity.setOnClickListener {
            val intent = Intent(this, UdaraActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchWeatherData(city: String) {
        progressBar.visibility = View.VISIBLE // Tampilkan ProgressBar
        val request = CityRequest(kota = city)
        val call = RetrofitClient2.instance.getWeather(request)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                progressBar.visibility = View.GONE // Sembunyikan ProgressBar
                if (response.isSuccessful) {
                    val weatherData = response.body()?.data
                    val weatherDescription = response.body()?.weather

                    // Update UI
                    if (weatherData != null) {
                        tvTemperature.text = "${weatherData.temp}°C"
                        tvWeatherDescription.text = weatherDescription
                        tvWindSpeed.text = "${weatherData.wind_speed} km/j"
                        tvArahAngin.text = "${weatherData.wind_degree}°"
                        humidityPercent.text = "${weatherData.humidity}%"
                        uvIndexValue.text = "${weatherData.uv} High"
                    }
                } else {
                    Log.e("MainActivity", "API Error: ${response.message()}")
                    Toast.makeText(this@MainActivity, "Gagal mengambil data cuaca", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                progressBar.visibility = View.GONE // Sembunyikan ProgressBar
                Log.e("MainActivity", "Network Error: ${t.message}")
                Toast.makeText(this@MainActivity, "Kesalahan jaringan", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchWeatherPrediction(city: String, jam: String) {
        progressBar.visibility = View.VISIBLE // Tampilkan ProgressBar
        val request = CityRequest2(kota = city, jam = jam)
        val call = RetrofitClient3.instance.getHoursly(request)

        call.enqueue(object : Callback<WeatherResponse2> {
            override fun onResponse(call: Call<WeatherResponse2>, response: Response<WeatherResponse2>) {
                progressBar.visibility = View.GONE // Sembunyikan ProgressBar
                if (response.isSuccessful) {
                    val weatherData = response.body()?.data

                    if (!weatherData.isNullOrEmpty() && weatherData.size >= 4) {
                        // Update UI
                        tvJamSekarang.text = formatTime(weatherData[0].time)
                        tvTempSekarang.text = "${weatherData[0].temp}°C"

                        tvJam1.text = formatTime(weatherData[1].time)
                        tvTempJam1.text = "${weatherData[1].temp}°C"

                        tvJam2.text = formatTime(weatherData[2].time)
                        tvTempJam2.text = "${weatherData[2].temp}°C"

                        tvJam3.text = formatTime(weatherData[3].time)
                        tvTempJam3.text = "${weatherData[3].temp}°C"
                    } else {
                        Log.e("MainActivity", "Data tidak mencukupi untuk prediksi cuaca per jam.")
                        Toast.makeText(this@MainActivity, "Data tidak mencukupi untuk prediksi cuaca", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("MainActivity", "API Error: ${response.message()}")
                    Toast.makeText(this@MainActivity, "Gagal mengambil data prediksi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse2>, t: Throwable) {
                progressBar.visibility = View.GONE // Sembunyikan ProgressBar
                Log.e("MainActivity", "Network Error: ${t.message}")
                Toast.makeText(this@MainActivity, "Kesalahan jaringan", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun formatTime(time: String?): String {
        return try {
            if (time.isNullOrEmpty()) return "N/A"
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = inputFormat.parse(time)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error formatting time: ${e.message}")
            "N/A"
        }
    }
}
