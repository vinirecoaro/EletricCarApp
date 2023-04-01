package com.example.eletriccarapp.domain

data class Carro(
    val id: String,
    val preco: String,
    val bateria: String,
    val potencia: String,
    val recarga: String,
    val urlPhoto: String,
    var isFavorite: Boolean
)

