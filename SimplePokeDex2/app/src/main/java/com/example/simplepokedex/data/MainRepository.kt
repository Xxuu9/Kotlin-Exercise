package com.example.simplepokedex.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplepokedex.network.ApiService
import com.example.simplepokedex.network.models.PokemonInfo
import com.example.simplepokedex.network.models.PokemonListWrapper
import retrofit2.HttpException
import java.io.IOException

class MainRepository(private val apiService: ApiService) {

    private val _pokemonInfos = MutableLiveData<List<PokemonInfo>>()
    val pokemonInfos: LiveData<List<PokemonInfo>> get() = _pokemonInfos

    suspend fun loadPokemons() {
        val result: PokemonListWrapper? = try {
            apiService.getPokemons()
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        result?.let {
            _pokemonInfos.value = it.results
        }
    }
}