package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnPlan: Button = findViewById(R.id.btnPlaneaCita)
        btnPlan.setOnClickListener {
            val intent = Intent(this, Cuestionario:: class.java)
            startActivity(intent)
        }

        val btnConnet: Button = findViewById(R.id.btnConectaPareja)
        btnConnet.setOnClickListener {
            val intent = Intent(this, ConnectYourDate:: class.java)
            startActivity(intent)
        }
    }
}