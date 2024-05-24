package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userName: TextView = findViewById(R.id.UsernameNombreUsario)
        val email:  String = LogIn.EMAIL_ADDRESS_USER

        db.collection("users").document(email).get().addOnSuccessListener {
            userName.text= it.get("name") as String?
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