package com.example.bibliotecavirtual.model

import java.io.Serializable

data class MiniConto(
    val foto: String,
    val nome: String,
    val genero: String,
    val sinopse: String,
    val texto: String
) : Serializable
