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


class SignIn : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sign_in)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val editTextEmailAddress: EditText = findViewById(R.id.editTextEmailAddress)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val editTextRePassword: EditText = findViewById(R.id.editTextRePassword)
        val btnRegister: Button = findViewById(R.id.register_button)
        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextPhone: EditText = findViewById(R.id.editTextPhone)

        // Listener para el botón de registro
        btnRegister.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()
            val rePassword = editTextRePassword.text.toString()

            if (password == rePassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            db.collection("users").document(email).set(
                                hashMapOf(
                                    "name" to editTextName.text.toString(),
                                    "phone" to editTextPhone.text.toString()
//                                "partner" to editTextEmailAddress.text.toString()
                                )
                            )
                            Toast.makeText(
                                this, "Registro exitoso, vuelva a iniciar sesión",
                                Toast.LENGTH_SHORT
                            ).show()
                            // El usuario se registró exitosamente, redirige al usuario a la actividad principal
                            val intent = Intent(this, LogIn::class.java)
                            startActivity(intent)
                        } else {
                            // La creación de la cuenta falló, muestra un mensaje de error al usuario
                            Toast.makeText(this, "Registro fallido", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }

        val btn: Button = findViewById(R.id.logIn_button)
        btn.setOnClickListener {
            val intent = Intent(this, LogIn:: class.java)
            startActivity(intent)
        }
    }

}