package com.example.mvvmexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val repository = MainRepository()

    val resultLiveData = repository.resultLiveData

    // data without mutable is not editable, it is read only
    //val resultLiveData = MutableLiveData<String>()

    init {
        viewModelScope.launch{
            repository.loadData()
        }
    }
}