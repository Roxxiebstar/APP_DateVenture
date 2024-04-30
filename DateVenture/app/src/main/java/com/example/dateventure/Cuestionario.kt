package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cuestionario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuestionario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            val btnRestaurante: Button = findViewById(R.id.btnRestaurants)
            btnRestaurante.setOnClickListener {
                val intent: Intent = Intent(this, Restaurante:: class.java)
                startActivity(intent)
            }

            val btnTravel: Button = findViewById(R.id.btnTravel)
            btnTravel.setOnClickListener {
                val intent: Intent = Intent(this, SignIn:: class.java)
                startActivity(intent)
            }
        val btnGreen: Button = findViewById(R.id.btnGreenPlaces)
        btnGreen.setOnClickListener {
            val intent: Intent = Intent(this, SignIn:: class.java)
            startActivity(intent)
        }
        val btnHome: Button = findViewById(R.id.btnHome)
        btnHome.setOnClickListener {
            val intent: Intent = Intent(this, SignIn:: class.java)
            startActivity(intent)
           }
        }
    }