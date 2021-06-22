package com.kim.deryk.byung.dogviewerapplication.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed
import kotlinx.coroutines.launch


class ImageViewModel(
    private val repository: DogRepository,
    private val dogBreedName: String
): ViewModel(){
    val dogBreedDetails = repository.dogBreedDetails
    init {
        viewModelScope.launch {
            repository.loadDogBreedDetails(dogBreedName)
        }
    }
}

class ImageViewModelFactory(
    private val repository: DogRepository,
    private val dogBreedName: String
    ): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(repository, dogBreedName) as T
        }
        throw IllegalArgumentException("Invalid ViewModel")
    }

}












