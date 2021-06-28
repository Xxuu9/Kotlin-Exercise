package com.example.simplepokedex.ui.details

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.simplepokedex.data.MainRepository
import com.example.simplepokedex.network.models.PokemonDetails
import com.example.simplepokedex.util.BundleKeys
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: MainRepository,
    private val handle: SavedStateHandle
): ViewModel(){

    private val pokemonName: String = handle.get<String>(BundleKeys.POKEMON_NAME) ?: ""
    val isPokemonVisible = handle.getLiveData(BundleKeys.IS_DETAILS_VISIBLE, false)

    val pokemonDetails = repository.pokemonDetails

    private val _share = MutableLiveData<PokemonDetails?>()
    val share: LiveData<PokemonDetails?> get() = _share

    private val _browse = MutableLiveData<PokemonDetails?>()
    val browse: LiveData<PokemonDetails?> get() = _browse

    init {
        viewModelScope.launch {
            repository.loadPokemonDetails(pokemonName)
        }
    }

    fun onVisibilityChange(isVisible: Boolean) {
        handle.set(BundleKeys.IS_DETAILS_VISIBLE, isVisible)
    }

    fun onShareClick() {
        _share.value = pokemonDetails.value
    }

    fun onShareComplete() {
        _share.value = null
    }

    fun onBrowseClick() {
        _browse.value = pokemonDetails.value
    }

    fun onBrowseClickComplete() {
        _browse.value = null
    }
}

class DetailsViewModelFactory(private val repository: MainRepository,
                              owner: SavedStateRegistryOwner,
                              defaultArgs: Bundle?):
    AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(repository, handle) as T
        }

        throw IllegalArgumentException("Invalid ViewModel")
    }
}