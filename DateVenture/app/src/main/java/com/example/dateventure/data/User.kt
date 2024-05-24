package com.example.dateventure.data

import androidx.room.Entity


@Entity
     (tableName = "Users_table")
data class User(
     val id: Int,
     val firstName: String,
     val lastName: String,
     val correo: String,
     val CellphoneNumber: String,
     val email: String,
     val pasword:String
)