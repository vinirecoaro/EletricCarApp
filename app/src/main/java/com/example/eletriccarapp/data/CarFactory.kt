package com.example.eletriccarapp.data

import com.example.eletriccarapp.domain.Carro

object CarFactory {

    val list = listOf<Carro>(
        Carro(
            id = "1",
            price = "R$ 300.000",
            battery = "300 kWh",
            power = "200 cv",
            recharge = "30 minutos",
            urlPhoto = "www.google.com.br"
        ),
        Carro(
            id = "1",
            price = "R$ 250.000",
            battery = "220 kWh",
            power = "250 cv",
            recharge = "20 minutos",
            urlPhoto = "www.google.com.br"
        )
    )
}