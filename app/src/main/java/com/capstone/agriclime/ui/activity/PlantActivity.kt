package com.capstone.agriclime.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.agriclime.R
import com.capstone.agriclime.adapter.PlantAdapter
import com.capstone.agriclime.network.Plant
import com.capstone.agriclime.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.capstone.agriclime.network.PlantResponse

class PlantActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var plantAdapter: PlantAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.news_items_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi ProgressBar
        progressBar = findViewById(R.id.progress_bar)

        // Inisialisasi adapter dengan list kosong
        plantAdapter = PlantAdapter(emptyList())
        recyclerView.adapter = plantAdapter

        // Ambil data dari API
        fetchPlants()

        // Tombol kembali
        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchPlants() {
        // Tampilkan ProgressBar sebelum memulai request
        progressBar.visibility = View.VISIBLE

        RetrofitClient.instance.getPlants().enqueue(object : Callback<PlantResponse> {
            override fun onResponse(call: Call<PlantResponse>, response: Response<PlantResponse>) {
                // Sembunyikan ProgressBar saat respon diterima
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val plants = response.body()?.data // Mengakses data dari response
                    if (plants != null && plants.isNotEmpty()) {
                        plantAdapter.updatePlants(plants)
                    } else {
                        Log.i("PlantActivity", "Data kosong")
                    }
                } else {
                    Log.e("PlantActivity", "Gagal mengambil data: ${response.message()}")
                    Toast.makeText(
                        this@PlantActivity,
                        "Gagal mengambil data: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<PlantResponse>, t: Throwable) {
                // Sembunyikan ProgressBar jika terjadi error
                progressBar.visibility = View.GONE
                Log.e("PlantActivity", "Kesalahan jaringan: ${t.message}")
                Toast.makeText(
                    this@PlantActivity,
                    "Kesalahan jaringan: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
