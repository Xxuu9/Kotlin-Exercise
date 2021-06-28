package com.example.simplepokedex.ui.main

import androidx.lifecycle.*
import com.example.simplepokedex.data.MainRepository
import com.example.simplepokedex.network.models.PokemonInfo
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {

    val pokemonInfos: LiveData<List<PokemonInfo>> = repository.pokemonInfos

    private val _navigateToDetails = MutableLiveData<PokemonInfo?>()
    val navigateToDetails: LiveData<PokemonInfo?> get() = _navigateToDetails

    init {
        viewModelScope.launch {
            repository.loadPokemons()
        }
    }

    fun onPokemonClick(pokemonInfo: PokemonInfo?) {
        _navigateToDetails.value = pokemonInfo
    }

    fun onNavigateToDetailComplete() {
        _navigateToDetails.value = null
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