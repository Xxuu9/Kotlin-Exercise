package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(
    private val repository: DogRepository,
    state: SavedStateHandle
) : ViewModel() {
    private val mainTab: MainTab = state.get(BundleKeys.MAIN_TAB) ?: MainTab.ALL

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val searchKey = MutableStateFlow("")
    fun onNewSearchKey(key: String) {
        searchKey.value = key
    }

    val breedList: LiveData<List<Breed>> = searchKey
        .onEach {
            _isLoading.value = true
        }
        .debounce{
            if (it.isBlank()) 0 else 2000
        }
        .flatMapLatest { query ->
            //filtering part
        repository.breedList.map {
            it.filter { breed ->
                val isSelectedTab = mainTab == MainTab.ALL || breed.isFaved
                val isSearch = query.isBlank() || breed.title.contains(query, ignoreCase = true)
                isSelectedTab && isSearch
            }
        }
    }.onEach {
        _isLoading.value = false
    }
    .asLiveData()

    
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

    fun onNavigateToDetailFinished() {
        _navigateToDetails.value = null
    }

    fun onFaveClick(breed: Breed) {
        viewModelScope.launch {
            repository.updateBreed(Breed(
                breed.breed,
                breed.subBreed,
                !breed.isFaved
            )
            )
        }
    }
}


class MainViewModelFactory(private val repository: DogRepository,
                           owner: SavedStateRegistryOwner,
                           defaultArgs: Bundle?):
    AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, handle) as T
        }

        throw IllegalArgumentException("Invalid ViewModel")
    }

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            return MainViewModel(repository) as T
//        }
//
//        throw IllegalArgumentException("Invalid View Model")
//    }
}
