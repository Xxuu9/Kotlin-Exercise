package com.kim.deryk.byung.dogviewerapplication.ui.details

import android.os.Bundle
import android.telecom.Call
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryOwner
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed
import kotlinx.coroutines.launch


class ImageViewModel(
    private val repository: DogRepository,
    private val handle: SavedStateHandle
): ViewModel(){
    val dogBreedDetails = repository.dogBreedDetails
    val breed: Breed = handle.get<Breed>(BundleKeys.DOG_BREED) ?: Breed("", "")

    init {
        viewModelScope.launch {
            repository.loadDogBreedDetails(breed)
        }
    }
}


class ImageViewModelFactory(
    private val repository: DogRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
): AbstractSavedStateViewModelFactory(owner, defaultArgs){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(repository, handle) as T

        }
        throw IllegalArgumentException("Invalid ViewModel")
    }
}













