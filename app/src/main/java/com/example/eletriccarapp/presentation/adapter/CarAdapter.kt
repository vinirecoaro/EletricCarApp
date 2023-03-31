package com.example.eletriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R

class CarAdapter(private val cars: Array<String>): RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = cars[position]
    }

    override fun getItemCount(): Int = cars.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView
        init {
            textView = view.findViewById(R.id.text_price_value)
        }
    }

}

