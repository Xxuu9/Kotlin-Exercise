package com.example.simplepokedex.network

import com.example.simplepokedex.network.models.PokemonListWrapper
import retrofit2.http.GET

interface ApiService {
    @GET("pokemon/?limit=898")
    suspend fun getPokemons(): PokemonListWrapper
}