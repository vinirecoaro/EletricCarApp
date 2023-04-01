package com.example.eletriccarapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.data.CarsApi
import com.example.eletriccarapp.domain.Carro
import com.example.eletriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

class CarFragment: Fragment() {

    lateinit var fabCalculate: FloatingActionButton
    lateinit var carsList: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var noInternetImage: ImageView
    lateinit var noInternetText: TextView
    lateinit var carsApi: CarsApi

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
        setupRetrofit()
        setupView(view)
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)){
        getAllCars()
        //callService() //Outra forma de chamar serviço
        }else{
            emptyState()
        }
    }

    fun setupRetrofit(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carsApi = retrofit.create(CarsApi::class.java)
    }

    fun getAllCars(){
        carsApi.getAllCars().enqueue(object : Callback<List<Carro>>{
            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if(response.isSuccessful){
                    progress.visibility = View.GONE
                    noInternetImage.visibility = View.GONE
                    noInternetText.visibility = View.GONE
                    response.body()?.let{
                        setupList(it)
                    }
                }else{
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun emptyState(){
        progress.visibility = View.GONE
        carsList.visibility = View.GONE
        noInternetImage.visibility = View.VISIBLE
        noInternetText.visibility = View.VISIBLE
    }

    fun setupView(view: View){
        view.apply{
            fabCalculate = findViewById(R.id.fab_calculate)
            carsList = findViewById(R.id.rv_cars)
            progress = findViewById(R.id.pb_loader)
            noInternetImage = findViewById(R.id.image_empty_state)
            noInternetText = findViewById(R.id.text_no_wifi)
        }
    }

    fun setupList(list: List<Carro>){
        val adapter = CarAdapter(carsArray)
        carsList.visibility = View.VISIBLE
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

    fun checkForInternet(context: Context?): Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network  = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }else{
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected

        }

    }

    //Usar o retrofit como abstração do AsyncTask
    inner class Mytask: AsyncTask<String, String, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "Iniciando ...")
            progress.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null
            try{
                val urlBase = URL(url[0])
                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                )

                val responseCode = urlConnection.responseCode

                if(responseCode == HttpURLConnection.HTTP_OK){
                    var response = urlConnection.inputStream.bufferedReader().use{it.readText()}
                    publishProgress(response)
                }else{
                    Log.e("Erro", "Serviço indisponível!")
                }


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
                }
                progress.visibility = View.GONE
                noInternetImage.visibility = View.GONE
                noInternetText.visibility = View.GONE
                //setupList()
            }catch(e: Exception){
                Log.e("Erro", e.message.toString())
            }
        }

    }
}