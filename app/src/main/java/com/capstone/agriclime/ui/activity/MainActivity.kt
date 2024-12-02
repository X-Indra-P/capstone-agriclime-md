package com.capstone.agriclime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.capstone.agriclime.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_dashboard)

        // Bind News Icon
        val newsIcon = findViewById<ImageButton>(R.id.newsIcon)
        newsIcon.setOnClickListener {
            // Navigasi ke NewsActivity
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
    }
}
