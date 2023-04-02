package com.example.eletriccarapp.data

import com.example.eletriccarapp.domain.Carro

object CarFactory {

    val list = listOf<Carro>(
        Carro(
            id = 1,
            preco = "R$ 300.000",
            bateria = "300 kWh",
            potencia = "200 cv",
            recarga = "30 minutos",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        ),
        Carro(
            id = 1,
            preco = "R$ 250.000",
            bateria = "220 kWh",
            potencia = "250 cv",
            recarga = "20 minutos",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        )
    )
}