package com.example.eletriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.domain.Carro

class CarAdapter(private val cars: List<Carro>):
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var carItemListener : (Carro) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.preco.text = cars[position].preco
        holder.bateria.text = cars[position].bateria
        holder.potencia.text = cars[position].potencia
        holder.recarga.text = cars[position].recarga
        holder.favorite.setOnClickListener{
            val carro = cars[position]
            carItemListener(carro)
            setupFavorite(carro, holder)
        }
    }

    private fun setupFavorite(
        carro: Carro,
        holder: ViewHolder
    ) {
        carro.isFavorite = !carro.isFavorite
        if (carro.isFavorite) {
            holder.favorite.setImageResource(R.drawable.star_full)
        } else {
            holder.favorite.setImageResource(R.drawable.star)
        }
    }

    override fun getItemCount(): Int = cars.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView
        val favorite: ImageView
        init {
            view.apply{
                preco = findViewById(R.id.text_price_value)
                bateria = findViewById(R.id.text_battery_value)
                potencia = findViewById(R.id.text_power_value)
                recarga = findViewById(R.id.text_recharge_value)
                favorite = findViewById(R.id.image_favorite)
            }
        }
    }

}

