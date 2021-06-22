package com.example.dogbreed.network

import com.example.dogbreed.network.models.BreedList
import retrofit2.http.GET

interface ApiService {
    @GET("breeds/list/all")
    suspend fun getDogs(): BreedList
}
