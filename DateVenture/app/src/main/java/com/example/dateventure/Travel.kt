package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Travel : AppCompatActivity() {
    private val selectedItems = mutableSetOf<Int>()
    private lateinit var listView: ListView
    private val db = FirebaseFirestore.getInstance()
    private lateinit var items: MutableList<String> // Definir items en el alcance de la clase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_travel)

        val btnNext: Button = findViewById(R.id.buttonContinue)
        btnNext.setOnClickListener {
            // Obtener las opciones seleccionadas por el usuario
            val selectedOptions = selectedItems.map { items[it] }

            // Obtener el correo electrónico del usuario actual
            val email = FirebaseAuth.getInstance().currentUser?.email

            // Verificar que el correo electrónico no sea nulo
            email?.let {
                // Guardar las preferencias del usuario
                guardarPreferenciasUsuario(email, selectedOptions)
            }

            // Continuar con la lógica para ir a la siguiente actividaad
            val intent = Intent(this, Cuestionario::class.java)
            startActivity(intent)
        }

        // Encuentra el ListView en el diseño
        listView = findViewById(R.id.travelPreferencesListView)

        // Crea una lista de datos
        items = mutableListOf("MEDELLIN", "CALI", "BOGOTÁ", "BICARAMANGA", "SANTA MARTA")

        // Crea el ArrayAdapter
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_multiple_choice,
            items
        )

        // Enlaza el adaptador con el ListView
        listView.adapter = adapter

        // Notificar al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged()

        listView.setOnItemClickListener { _, _, position, _ ->
            if (selectedItems.contains(position)) {
                selectedItems.remove(position)  // Deseleccionar
            } else {
                selectedItems.add(position)  // Seleccionar
            }
        }

        // Restaurar las selecciones previas del usuario si existen
        savedInstanceState?.let {
            val restoredItems = it.getIntegerArrayList("selectedItems")
            restoredItems?.forEach { position ->
                listView.setItemChecked(position, true)  // Restaurar la selección
                selectedItems.add(position)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("selectedItems", ArrayList(selectedItems))
    }

    // Función para guardar las preferencias del usuario en Firestore
    private fun guardarPreferenciasUsuario(email: String, preferenciasTravel: List<String>) {
        // Referencia al documento del usuario en Firestore
        val referenciaUsuario = db.collection("users").document(email)

        // Obtener las preferencias de comida del usuario si ya existen en el documento
        referenciaUsuario.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val preferencias = document["preferencias"] as? Map<String, Any>
                val preferenciasFood = preferencias?.get("preferenciasFood") as? List<String>
                if (preferenciasFood != null) {
                    // Combinar las preferencias de comida y de viaje en un solo mapa
                    val preferenciasActualizadas = hashMapOf(
                        "preferencias" to hashMapOf(
                            "preferenciasFood" to preferenciasFood,
                            "preferenciasTravel" to preferenciasTravel
                        )
                    )

                    // Actualizar el documento del usuario con las preferencias combinadas
                    referenciaUsuario.update(preferenciasActualizadas as Map<String, Any>)
                        .addOnSuccessListener {
                            // La información se ha guardado exitosamente en Firestore
                        }
                        .addOnFailureListener { e ->
                            // Ocurrió un error al intentar guardar la información en Firestore
                        }
                }
            }
        }.addOnFailureListener { e ->
            // Ocurrió un error al intentar obtener el documento del usuario
        }
    }
}
