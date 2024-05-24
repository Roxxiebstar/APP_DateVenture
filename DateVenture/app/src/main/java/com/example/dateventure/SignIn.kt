package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SignIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sign_in)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val editTextEmailAddress: EditText = findViewById(R.id.editTextEmailAddress)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val btnRegister: Button = findViewById(R.id.register_button)

        // Listener para el botón de registro
        btnRegister.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // El usuario se registró exitosamente, redirige al usuario a la actividad principal
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // La creación de la cuenta falló, muestra un mensaje de error al usuario
                        Toast.makeText(this, "Registro fallido", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        val btn: Button = findViewById(R.id.logIn_button)
        btn.setOnClickListener {
            val intent: Intent = Intent(this, LogIn:: class.java)
            startActivity(intent)
        }
    }

}