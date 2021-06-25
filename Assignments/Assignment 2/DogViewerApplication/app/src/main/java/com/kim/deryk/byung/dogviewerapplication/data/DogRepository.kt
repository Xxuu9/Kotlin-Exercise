package com.kim.deryk.byung.dogviewerapplication.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.data.network.ApiService
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed
import com.kim.deryk.byung.dogviewerapplication.data.network.models.BreedDetails
import com.kim.deryk.byung.dogviewerapplication.data.network.models.BreedList
import retrofit2.HttpException
import java.io.IOException

class DogRepository(private val apiService: ApiService) {

    private val _dogBreeds = MutableLiveData<List<Breed>>()
    val dogBreeds: LiveData<List<Breed>> get() = _dogBreeds

    private val _dogBreedDetails = MutableLiveData<BreedDetails>()
    val dogBreedDetails: LiveData<BreedDetails> get() = _dogBreedDetails

    suspend fun loadDogs(){
        val response = try {
            apiService.getBreed()
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        response?.let{
            _dogBreeds.value = it.getBreeds()
        }

    }

    suspend fun loadDogBreedDetails(breed: Breed){
        //Log.e("txx2", breed.title)
        try {
            if (breed.subBreed != ""){
                _dogBreedDetails.value = apiService.getDetailsWithBothBreeds(breed.breed, breed.subBreed)
            }
            else {
                _dogBreedDetails.value = apiService.getDetailsWithBreed(breed.breed)
            }
        }
        catch (e: Exception) {
            Log.e("txx2", "Error $e")
        }
        //Log.e("txx2", _dogBreedDetails.value.toString())
    }

}