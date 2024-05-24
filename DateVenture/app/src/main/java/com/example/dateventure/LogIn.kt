package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LogIn : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.log_in)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Obtener referencias a los EditText y botones
        val editTextEmailAddress: EditText = findViewById(R.id.editTextEmailAddress)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val btnLogIn: Button = findViewById(R.id.logIn_button)

        // Listener para el botón de inicio de sesión
        btnLogIn.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        EMAIL_ADDRESS_USER = email
                        // El inicio de sesión fue exitoso, redirige al usuario a la actividad principal
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // El inicio de sesión falló, muestra un mensaje de error al usuario
                        Toast.makeText(
                            this,
                            "Inicio de sesión fallido",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        val btnSignIn: Button = findViewById(R.id.register_button)
        btnSignIn.setOnClickListener {
            val intent: Intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }

    companion object {
        var EMAIL_ADDRESS_USER: String = ""
    }
}
