package com.example.dateventure

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GreenPlaces : AppCompatActivity() {
    private val selectedItems = mutableSetOf<Int>()
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_green_places)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnNext: Button = findViewById(R.id.buttonContinue)
        btnNext.setOnClickListener {
            val intent = Intent(this, Cuestionario:: class.java)
            startActivity(intent)
        }

        // Encuentra el ListView en el diseño
        val listView: ListView = findViewById(R.id.greenSpacePreferencesListView)

        // Crea una lista de datos
        val items = mutableListOf("Picnic in the Park", "Visit to a Botanical Garden", "Outdoor Drawing Session", "Nature Bike Ride", "Volunteering at a Community Garden")

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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("selectedItems", ArrayList(selectedItems))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredItems = savedInstanceState.getIntegerArrayList("selectedItems")
        restoredItems?.forEach { position ->
            listView.setItemChecked(position, true)  // Restaura la selección
            selectedItems.add(position)
        }
    }
}
