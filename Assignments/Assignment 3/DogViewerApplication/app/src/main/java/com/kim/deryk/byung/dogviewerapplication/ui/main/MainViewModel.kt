package com.kim.deryk.byung.dogviewerapplication.ui.main

import androidx.lifecycle.*
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(
    private val repository: DogRepository
) : ViewModel() {

    val breedList = repository.breedList
    private val _navigateToDetails = MutableLiveData<Breed?>()
    val navigateToDetails: LiveData<Breed?> get() = _navigateToDetails

    init {
        viewModelScope.launch {
            repository.loadDogList()
        }
    }

    fun onBreedClicked(breed: Breed) {
        _navigateToDetails.value = breed
    }

    fun onFaveClick(breed: Breed) {
        breed.isFaved = !breed.isFaved
        viewModelScope.launch {
            repository.updateBreedInfo(breed)
        }
    }

    fun onNavigateToDetailFinished() {
        _navigateToDetails.value = null
    }
}

class MainViewModelFactory(private val repository: DogRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Invalid View Model")
    }
}
