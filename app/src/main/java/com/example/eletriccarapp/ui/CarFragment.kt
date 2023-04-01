package com.example.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragment: Fragment() {

    lateinit var fabCalculate: FloatingActionButton
    lateinit var carsList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList(view)
        setupListeners()
    }

    fun setupView(view: View){
        view.apply{
            fabCalculate = findViewById(R.id.fab_calculate)
            carsList = findViewById(R.id.rv_cars)
        }
    }

    fun setupList(view: View){
        val adapter = CarAdapter(CarFactory.list)
        carsList.layoutManager = LinearLayoutManager(view.context)
        carsList.adapter = adapter
    }

    fun setupListeners(){
        fabCalculate.setOnClickListener{
            startActivity(Intent(context, AutonomyCalculateActivity::class.java))
        }
    }
}