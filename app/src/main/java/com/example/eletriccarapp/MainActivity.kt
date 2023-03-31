package com.example.eletriccarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView

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
        price = findViewById(R.id.edit_price)
        btnCalculate = findViewById(R.id.button_calculate)
        kmPercorrido = findViewById(R.id.edit_run_distance)
        resultado = findViewById(R.id.text_result)
    }

    fun setupListeners(){
        btnCalculate.setOnClickListener{
            calculate()
        }
    }

    fun calculate(){
        val price = price.text.toString().toFloat()
        val km = kmPercorrido.text.toString().toFloat()
        val result = price/km
        resultado.text = result.toString()
    }

}