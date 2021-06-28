package com.example.simplepokedex.network

import com.example.simplepokedex.network.models.PokemonDetails
import com.example.simplepokedex.network.models.PokemonListWrapper
import com.example.simplepokedex.ui.details.PokemonDetailsActivity
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/?limit=898")
    suspend fun getPokemons(): PokemonListWrapper

    @GET("pokemon/{pokemon_name}")
    suspend fun getPokemonDetails(@Path("pokemon_name") pokemonName: String): PokemonDetails
}