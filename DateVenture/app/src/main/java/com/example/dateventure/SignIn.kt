package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SignMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSignIn: Button = findViewById(R.id.register_button)
        btnSignIn.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }

        val btn: Button = findViewById(R.id.logIn_button)
        btn.setOnClickListener {
            val intent: Intent = Intent(this, LogIn:: class.java)
            startActivity(intent)
        }
    }

}