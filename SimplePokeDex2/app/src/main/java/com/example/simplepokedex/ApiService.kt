package com.example.simplepokedex

import com.example.simplepokedex.network.models.PokemonListWrapper
import retrofit2.http.GET

interface ApiService {
    @GET("pokemon/")
    suspend fun getPokemons(): PokemonListWrapper
}