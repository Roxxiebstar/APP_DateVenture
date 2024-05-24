package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ConnectYourDate : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_connect_your_date)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val btnSignIn: Button = findViewById(R.id.buttonDate)
        btnSignIn.setOnClickListener {
            val editTextEmailAddress: EditText = findViewById(R.id.editTextParner)
            EMAIL_ADDRESS_PARTNER = editTextEmailAddress.text.toString()
            val intent = Intent(this, PopUpdate:: class.java)
            startActivity(intent)
        }

        val btnNext: Button = findViewById(R.id.buttonContinue)
        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }

    }

    companion object {
        var EMAIL_ADDRESS_PARTNER: String = ""
    }

}