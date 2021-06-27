package com.example.simplepokedex.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplepokedex.R
import com.example.simplepokedex.SimplePokeDexApplication
import com.example.simplepokedex.data.MainRepository
import com.example.simplepokedex.ui.details.PokemonDetailsActivity
import com.example.simplepokedex.util.BundleKeys

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
            viewModel.onPokemonClick(pokemonInfo)
        })
        recyclerView.adapter = adapter

        viewModel.pokemonInfos.observe(this) {
            adapter.pokemonList = it
            adapter.notifyDataSetChanged()
        }

        viewModel.navigateToDetails.observe(this){
            it?.let{
                val intent = Intent(this, PokemonDetailsActivity::class.java).apply{
                    putExtra(BundleKeys.POKEMON_NAME, it.name)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailComplete()
            }
        }





    }
}