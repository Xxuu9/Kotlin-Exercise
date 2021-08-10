package com.kim.deryk.byung.dogviewerapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.kim.deryk.byung.dogviewerapplication.data.database.BreedDatabase
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import com.kim.deryk.byung.dogviewerapplication.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DogRepository(private val apiService: ApiService,
                    private val database: BreedDatabase) {

    val breedList: Flow<List<Breed>> = database.getBreedDao().get().map { breedList ->
        breedList.map {
            it.toBreed()
        }
    }

    private val _randomImage = MutableLiveData<String>(null)
    val randomImage: LiveData<String> get() = _randomImage

    suspend fun loadDogList() {
        val response = try {
            apiService.getBreed()
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        response?.let {
            withContext(Dispatchers.IO) {
                database.getBreedDao().insertAll(it.getBreeds().map {
                    it.toLocalBreed()
                })
            }
        }
    }

    suspend fun loadRandomImage(breed: Breed) {
        val result = try {
            if (breed.subBreed.isEmpty()) {
                apiService.getRandomImage(breed.breed)
            } else {
                apiService.getRandomImage(breed.breed, breed.subBreed)
            }
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        result?.let {
            _randomImage.value = it.url
        }
    }

    suspend fun updateBreed(breed: Breed) {
        withContext(Dispatchers.IO) {
            database.getBreedDao().update(breed.toLocalBreed())
        }
    }
}