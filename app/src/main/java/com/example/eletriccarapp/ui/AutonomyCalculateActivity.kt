package com.example.eletriccarapp.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R
import java.net.HttpURLConnection
import java.net.URL

class AutonomyCalculateActivity: AppCompatActivity() {

    lateinit var price: EditText
    lateinit var btnCalculate: Button
    lateinit var kmPercorrido: EditText
    lateinit var resultado: TextView
    lateinit var btnClose: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_autonomy_calculate)
        setupView()
        setupListeners()
    }

    fun setupView(){
        price = findViewById(R.id.edit_price)
        btnCalculate = findViewById(R.id.button_calculate)
        kmPercorrido = findViewById(R.id.edit_run_distance)
        resultado = findViewById(R.id.text_result)
        btnClose = findViewById(R.id.image_close)
    }

    fun setupListeners(){
        btnCalculate.setOnClickListener{
            calculate()
        }
        btnClose.setOnClickListener{
            finish()
        }
    }

    fun calculate(){
        val price = price.text.toString().toFloat()
        val km = kmPercorrido.text.toString().toFloat()
        val result = price/km
        resultado.text = result.toString()
    }

}