package com.capstone.agriclime.ui.activity

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.capstone.agriclime.R

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news) // Menggunakan layout untuk NewsActivity

        // Menangani klik pada tombol back
        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            // Kembali ke halaman sebelumnya
            onBackPressed()
        }
    }
}
