package com.kim.deryk.byung.dogviewerapplication.ui.details

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import kotlinx.coroutines.launch

class ImageViewModel(
    private val repository: DogRepository,
    state: SavedStateHandle
): ViewModel() {
    private val selectedBreed = state.get<Breed>(BundleKeys.BREED) as Breed
    val imageUrl = repository.randomImage

    private val _shareImage = MutableLiveData<String?>()
    val shareImage: LiveData<String?> get() = _shareImage

    private val _browseImage = MutableLiveData<String?>()
    val browseImage: LiveData<String?> get() = _browseImage

    init {
        viewModelScope.launch {
            repository.loadRandomImage(selectedBreed)
        }
    }

    fun onBrowseClick() {
        _browseImage.value = imageUrl.value
    }

    fun onBrowseComplete() {
        _browseImage.value = null
    }

    fun share() {
        _shareImage.value = imageUrl.value
    }

    fun onShareComplete() {
        _shareImage.value = null
    }
}


class ImageViewModelFactory(
    private val repository: DogRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(repository, handle) as T
        }

        throw IllegalArgumentException("Invalid view model")
    }
}