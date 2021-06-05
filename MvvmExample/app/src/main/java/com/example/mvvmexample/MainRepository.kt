package com.example.mvvmexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository {
    private val _resultLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String> get() = _resultLiveData

    suspend fun loadData() {
        withContext(Dispatchers.Default) {
            delay(3000)
        }

        _resultLiveData.value = "Result is ready"
    }
}