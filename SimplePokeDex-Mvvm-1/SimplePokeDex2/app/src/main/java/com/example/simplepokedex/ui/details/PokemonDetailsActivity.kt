package com.example.simplepokedex.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.simplepokedex.R
import com.example.simplepokedex.SimplePokeDexApplication
import com.example.simplepokedex.data.MainRepository
import com.example.simplepokedex.databinding.ActivityPokemonDetailsBinding
import com.example.simplepokedex.network.models.PokemonDetails
import com.example.simplepokedex.util.BundleKeys

class PokemonDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailsViewModel> {
        val apiService = (application as SimplePokeDexApplication).serviceLocator.apiService
        val repository = MainRepository(apiService)
        DetailsViewModelFactory(repository, this, intent.extras)
    }

    private lateinit var binding: ActivityPokemonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pokemonDetails.observe(this) {
            populate(it)
        }

        viewModel.isPokemonVisible.observe(this) {
            binding.pokemonDetails.isVisible = it
            binding.showDetailsButton.text = getString(
                if (it) R.string.hide_details else R.string.show_details
            )
        }

        binding.showDetailsButton.setOnClickListener {
            viewModel.onVisibilityChange(!binding.pokemonDetails.isVisible)
        }
    }

    private fun populate(pokemonDetails: PokemonDetails) {
        with (binding) {
            pokemonTitle.text = pokemonDetails.title
            weightVal.text = pokemonDetails.formattedWeight
            heightVal.text = pokemonDetails.formattedHeight
            baseExpVal.text = pokemonDetails.baseExpString

            when {
                pokemonDetails.types.size == 1 -> {
                    typeVal1.text = pokemonDetails.types[0].typeString
                    typeVal2.isVisible = false
                }
                pokemonDetails.types.size >= 2 -> {
                    typeVal1.text = pokemonDetails.types[0].typeString
                    typeVal2.text = pokemonDetails.types[1].typeString
                }
                else -> {
                    typeVal1.isVisible = false
                    typeVal2.isVisible = false
                }
            }

            Glide.with(this@PokemonDetailsActivity)
                .load(pokemonDetails.spriteUrl)
                .centerInside()
                .into(pokemonImage)
        }
    }
}