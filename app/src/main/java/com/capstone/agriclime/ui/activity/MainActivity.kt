package com.capstone.agriclime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.capstone.agriclime.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Bind News Icon
        val newsIcon = findViewById<ImageButton>(R.id.newsIcon)
        newsIcon.setOnClickListener {
            // Navigasi ke PlantActivity
            val intent = Intent(this, PlantActivity::class.java)
            startActivity(intent)
        }

        // Bind Current Weather Card
        val currentWeatherCard = findViewById<CardView>(R.id.currentWeatherCard)
        currentWeatherCard.setOnClickListener {
            // Navigasi ke CuacaActivity
            val intent = Intent(this, CuacaActivity::class.java)
            startActivity(intent)
        }

        val warningCard = findViewById<CardView>(R.id.warningCard)
        warningCard.setOnClickListener {
            // Navigasi ke CuacaActivity
            val intent = Intent(this, PeringatanActivity::class.java)
            startActivity(intent)
        }

        val humidity = findViewById<CardView>(R.id.humiditycardview)
        humidity.setOnClickListener {
            // Navigasi ke CuacaActivity
            val intent = Intent(this, UdaraActivity::class.java)
            startActivity(intent)
        }
    }
}
