package com.example.bibliotecavirtual.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "mini_conto")
data class MiniConto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val foto: String,
    val nome: String,
    val genero: String,
    val sinopse: String,
    val texto: String
) : Serializable
