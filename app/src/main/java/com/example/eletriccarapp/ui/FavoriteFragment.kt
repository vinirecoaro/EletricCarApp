package com.example.eletriccarapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.local.CarRepository
import com.example.eletriccarapp.domain.Carro
import com.example.eletriccarapp.ui.adapter.CarAdapter

class FavoriteFragment: Fragment() {

    lateinit var favoriteCarsList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
    }

    private fun getCarsOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        return carList
    }



    fun setupList(){
        val cars = getCarsOnLocalDb()
        val carAdapter = CarAdapter(cars, isFavoriteScreen = true)
        favoriteCarsList.visibility = View.VISIBLE
        favoriteCarsList.layoutManager = LinearLayoutManager(context)
        favoriteCarsList.adapter = carAdapter

        carAdapter.carItemListener = {carro ->
            val isDeleted = CarRepository(requireContext()).delete(carro.id)
        }
    }

    fun setupView(view: View){
        view.apply{
            favoriteCarsList = findViewById(R.id.rv_cars_favorite)
        }
    }

}