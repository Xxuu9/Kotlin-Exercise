package com.example.simplepokedex.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplepokedex.R
import com.example.simplepokedex.SimplePokeDexApplication
import com.example.simplepokedex.data.MainRepository

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val apiService = (application as SimplePokeDexApplication).serviceLocator.apiService
        val repository = MainRepository(apiService)
        MainViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.pokemon_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = PokemonListAdapter(OnPokemonClickListener { pokemonInfo ->
            Toast.makeText(this, pokemonInfo.title, Toast.LENGTH_SHORT).show()
        })
        recyclerView.adapter = adapter

        viewModel.pokemonInfos.observe(this) {
            adapter.pokemonList = it
            adapter.notifyDataSetChanged()
        }
    }
}