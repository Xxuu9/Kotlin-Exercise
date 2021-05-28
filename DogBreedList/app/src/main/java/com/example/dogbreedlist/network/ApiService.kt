package com.example.dogbreedlist.network

import com.example.dogbreedlist.network.models.BreedList
import retrofit2.http.GET

interface ApiService {
    @GET("breeds/list/all")
    suspend fun getDogs(): BreedList
}