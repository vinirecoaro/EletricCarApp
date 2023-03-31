package com.example.eletriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.eletriccarapp.R

class MainActivity : AppCompatActivity() {

    lateinit var price: EditText
    lateinit var btnCalculate: Button
    lateinit var kmPercorrido: EditText
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()

    }

    fun setupView(){
        btnCalculate = findViewById(R.id.button_calculate)
    }

    fun setupListeners(){
        btnCalculate.setOnClickListener{
            startActivity(Intent(this, AutonomyCalculateActivity::class.java))
        }
    }

}