package com.example.bibliotecavirtual.model

import java.io.Serializable

data class Config(
    val theme: String,
    val lang: String
) : Serializable
