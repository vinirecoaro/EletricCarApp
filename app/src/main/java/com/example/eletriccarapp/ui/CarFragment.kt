package com.example.eletriccarapp.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.domain.Carro
import com.example.eletriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class CarFragment: Fragment() {

    lateinit var fabCalculate: FloatingActionButton
    lateinit var carsList: RecyclerView

    var carsArray: ArrayList<Carro> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callService()
        setupView(view)
        setupListeners()
    }

    fun setupView(view: View){
        view.apply{
            fabCalculate = findViewById(R.id.fab_calculate)
            carsList = findViewById(R.id.rv_cars)
        }
    }

    fun setupList(){
        val adapter = CarAdapter(carsArray)
        carsList.layoutManager = LinearLayoutManager(context)
        carsList.adapter = adapter
    }

    fun setupListeners(){
        fabCalculate.setOnClickListener{
        startActivity(Intent(context, AutonomyCalculateActivity::class.java))
        }
    }

    fun callService(){
        val urlBase = "https://igorbag.github.io/cars-api/cars.json"
        Mytask().execute(urlBase)
    }

    inner class Mytask: AsyncTask<String, String, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "Iniciando ...")
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null
            try{
                val urlBase = URL(url[0])
                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000

                var response = urlConnection.inputStream.bufferedReader().use{it.readText()}
                publishProgress(response)
            }catch(e: Exception){
                Log.e("Erro", "Erro ao realizar processamento ...")
            }finally {
                urlConnection?.disconnect()
                }
            return " "
            }

        override fun onProgressUpdate(vararg values: String?) {
            try{
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                for(i in 0 until jsonArray.length()){
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("IDDD -> ", id)

                    val price = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("IDDD -> ", price)

                    val battery = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("IDDD -> ", battery)

                    val power = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("IDDD -> ", power)

                    val recharge = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("IDDD -> ", recharge)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("IDDD -> ", urlPhoto)

                    val model = Carro(
                        id = id,
                        price = price,
                        battery = battery,
                        power = power,
                        recharge = recharge,
                        urlPhoto = urlPhoto
                    )
                    carsArray.add(model)
                    Log.d("Model -> ", model.toString())
                }
                setupList()
            }catch(e: Exception){
                Log.e("Erro", e.message.toString())
            }
        }

    }
}