package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class PopUpdate : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()
    val noFound: String = "Correo no resgistrado"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pop_up_date)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            }

        val userName: TextView = findViewById(R.id.textViewName)
        val userEmail: TextView = findViewById(R.id.textViewEmail)
        val emailPartner:  String = ConnectYourDate.EMAIL_ADDRESS_PARTNER
        userEmail.text = emailPartner

        if (emailPartner.isNotEmpty()) {
            db.collection("users").document(emailPartner)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            // Accede a los datos del documento
                            val dato = document.data?.get("name")
                            userName.text = dato.toString()
                            // Haz algo con los datos obtenidos
                            Log.d("Firestore", "Dato obtenido: $dato")

                            val btnNext: Button = findViewById(R.id.buttonContinue)
                            btnNext.setOnClickListener {
                                Toast.makeText(this, "Solicitud Enviada", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, ConnectYourDate:: class.java)
                                startActivity(intent)
                            }
                        } else {
                            val noFoundEmail: TextView = findViewById(R.id.textDate)
                            noFoundEmail.text = noFound
                            Toast.makeText(this, "El correo no está registrado", Toast.LENGTH_SHORT).show()
                            val btnNext: Button = findViewById(R.id.buttonContinue)
                            btnNext.setOnClickListener {
                                val intent = Intent(this, ConnectYourDate:: class.java)
                                startActivity(intent)
                            }
                        }
                    } else {
                        Log.e("Firestore", "Error al obtener documento", task.exception)
                    }
                }
        } else {
            Log.d("Firestore", "El correo electrónico del socio está vacío")
        }

        val  btnBack: Button = findViewById(R.id.buttonBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, ConnectYourDate:: class.java)
            startActivity(intent)
        }


    }
}