package com.example.eletriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.domain.Carro

class CarAdapter(private val cars: List<Carro>):
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.text = cars[position].price
        holder.battery.text = cars[position].battery
        holder.power.text = cars[position].power
        holder.recharge.text = cars[position].recharge
    }

    override fun getItemCount(): Int = cars.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val price: TextView
        val battery: TextView
        val power: TextView
        val recharge: TextView
        init {
            view.apply{
                price = findViewById(R.id.text_price_value)
                battery = findViewById(R.id.text_battery_value)
                power = findViewById(R.id.text_power_value)
                recharge = findViewById(R.id.text_recharge_value)
            }
        }
    }

}

