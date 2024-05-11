package com.example.dateventure

import ConnectSql
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Toast
class LogIn : AppCompatActivity() {

    private var connectSql = ConnectSql()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.log_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LogMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogIn: Button = findViewById(R.id.logIn_button)
        btnLogIn.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }

        val btn: Button = findViewById(R.id.register_button)
        btn.setOnClickListener { // Obtener los datos ingresados por el usuario
            val nombre = findViewById<EditText>(R.id.editTextName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val phone = findViewById<EditText>(R.id.editTextPhone).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.editTextRePassword).text.toString()

            // Verificar si las contraseñas coinciden
            if (password == confirmPassword) {
                // Guardar los datos en la base de datos
                saveUserToDatabase(nombre, email, phone, password)
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
     }
}
    private fun saveUserToDatabase(nombre: String, email: String, phone: String, password: String) {
        val conn = connectSql.dbConn()
        conn?.use { connection ->
            val statement = connection.prepareStatement(
                "INSERT INTO User (nombre, email, phone_number, password) VALUES (?, ?, ?, ?)"
            )
            statement.setString(1, nombre)
            statement.setString(2, email)
            statement.setString(3, phone)
            statement.setString(4, password)
            statement.executeUpdate()
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}