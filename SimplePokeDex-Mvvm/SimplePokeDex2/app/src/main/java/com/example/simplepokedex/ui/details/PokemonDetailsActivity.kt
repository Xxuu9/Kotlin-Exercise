package com.example.simplepokedex.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.example.simplepokedex.R
import com.example.simplepokedex.SimplePokeDexApplication
import com.example.simplepokedex.data.MainRepository
import com.example.simplepokedex.util.BundleKeys

class PokemonDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModels<DetailsViewModel> {
        val apiService = (application as SimplePokeDexApplication).serviceLocator.apiService
        val repository = MainRepository(apiService)
        val pokemonName = intent.getStringExtra(BundleKeys.POKEMON_NAME) ?: ""
        DetailsViewModelFactory(repository, pokemonName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemonName: TextView = findViewById(R.id.pokemon_name)
        viewModel.pokemonDetails.observe(this) {
            pokemonName.text = it.title
        }
    }
}