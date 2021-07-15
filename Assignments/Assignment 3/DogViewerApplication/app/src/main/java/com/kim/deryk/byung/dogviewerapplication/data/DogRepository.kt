package com.kim.deryk.byung.dogviewerapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.kim.deryk.byung.dogviewerapplication.data.database.BreedDatabase
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import com.kim.deryk.byung.dogviewerapplication.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DogRepository(private val apiService: ApiService,
                    private val breedDatabase: BreedDatabase) {

    //private val _breedList = MutableLiveData<List<Breed>?>()
    val breedList: LiveData<List<Breed>?> = breedDatabase.getBreedDao().get().map {
        it.map { localBreed-> localBreed.toDomain() }
    }

    private val _randomImage = MutableLiveData<String>()
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
            withContext(Dispatchers.IO){
                breedDatabase.getBreedDao().insertAll(it.getBreeds().map {breed -> breed.toLocal() })
                //_breedList.value = it.getBreeds()
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

    suspend fun updateBreedInfo(breed: Breed) {
        withContext(Dispatchers.IO) {
            breedDatabase.getBreedDao().update(breed.toLocal())
        }
    }
}