package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DogRepository) : ViewModel() {

    val dogBreeds: LiveData<List<Breed>> = repository.dogBreeds

    private val _navigateToDetails = MutableLiveData<Breed?>()
    val navigateToDetails: LiveData<Breed?> get() = _navigateToDetails

    init {
        viewModelScope.launch{
            repository.loadDogs()
        }
    }

    fun onDogBreedClick(breed: Breed?) {
        Log.e("txx", breed?.title.toString())
        _navigateToDetails.value = breed
    }

    fun onNavigateToDetailComplete() {
        _navigateToDetails.value = null
    }

}

class MainViewModelFactory(private val repository: DogRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Invalid ViewModel")
    }
}