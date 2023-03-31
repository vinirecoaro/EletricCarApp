package com.example.eletriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.presentation.adapter.CarAdapter

class MainActivity : AppCompatActivity() {

    lateinit var btnCalculate: Button
    lateinit var carsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupList()
    }

    fun setupView(){
        btnCalculate = findViewById(R.id.button_calculate)
        carsList = findViewById(R.id.rv_cars)
    }

    fun setupList(){
        var dados = arrayOf(
            "Cupcake", "Donut", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean"
        )
        val adapter = CarAdapter(dados)
        carsList.layoutManager = LinearLayoutManager(this)
        carsList.adapter = adapter
    }

    fun setupListeners(){
        btnCalculate.setOnClickListener{
            startActivity(Intent(this, AutonomyCalculateActivity::class.java))
        }
    }

}