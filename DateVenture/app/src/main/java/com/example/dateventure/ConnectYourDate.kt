package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        val editTextEmailAddress: EditText = findViewById(R.id.editTextEmailAddress)
        val editTextName: EditText = findViewById(R.id.editTextParner)

        val btnSignIn: Button = findViewById(R.id.buttonDate)
        btnSignIn.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            db.collection("users").document(email).set(
                hashMapOf("partner" to editTextName.text.toString())
            )

            val intent = Intent(this, PopUpdate:: class.java)
            startActivity(intent)
        }

        val btnNext: Button = findViewById(R.id.buttonContinue)
        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }

    }

//    mDialog = Dialog(this)
//    val btnDate: Button = findViewById(R.id.buttonDate)
//    btnDate.setOnClickListener {
//        mDialog.setContentView(R.layout.activity_pop_up_date)
//        mDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    }
}