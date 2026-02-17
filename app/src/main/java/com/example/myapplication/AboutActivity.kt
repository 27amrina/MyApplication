package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_simple)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Kalau mau set nama/email dari kode:
        // findViewById<TextView>(R.id.tv_name).text = "Nama Kamu"
        // findViewById<TextView>(R.id.tv_email).text = "email@domain.com"
    }
}
