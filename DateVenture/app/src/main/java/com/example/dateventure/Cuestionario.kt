package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

// creacion de clase questionario para creacion de citas
class Cuestionario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cuestionario)
        // Aquí podemos empezar la logica:)
        fun preferenciaRestaurant(view : View) { //se ha creado esta funcion del boton questionario, para que pueda ir de fragment_home a questionarioxml
            val intent= Intent(this, RestaurantsHoj::class.java).apply {}
            startActivity(intent)
        }
    }
}