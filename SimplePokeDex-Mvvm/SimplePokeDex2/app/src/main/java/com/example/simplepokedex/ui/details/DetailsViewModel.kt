package com.example.simplepokedex.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplepokedex.data.MainRepository
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: MainRepository,
    private val pokemonName: String
): ViewModel(){

    val pokemonDetails = repository.pokemonDetails

    init {
        viewModelScope.launch {
            repository.loadPokemonDetails(pokemonName)
        }
    }
}

class DetailsViewModelFactory(private val repository: MainRepository,
                              private val pokemonName: String
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(repository, pokemonName) as T
        }

        throw IllegalArgumentException("Invalid ViewModel")
    }
}