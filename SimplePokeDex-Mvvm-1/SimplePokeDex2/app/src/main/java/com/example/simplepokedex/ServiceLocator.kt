package com.example.simplepokedex

import com.example.simplepokedex.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator {
    companion object{
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}