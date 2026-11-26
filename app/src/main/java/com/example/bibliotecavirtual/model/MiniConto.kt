package com.example.bibliotecavirtual.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "mini_conto")
data class MiniConto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var foto: String,
    var nome: String,
    var genero: String,
    var sinopse: String,
    var texto: String
) : Serializable
