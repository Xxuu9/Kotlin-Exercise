package com.example.simplepokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplepokedex.data.MainRepository
import com.example.simplepokedex.network.models.PokemonInfo
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {

    val pokemonInfos: LiveData<List<PokemonInfo>> = repository.pokemonInfos

    init {
        viewModelScope.launch {
            repository.loadPokemons()
        }
    }
}

class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Invalid ViewModel")
    }
}