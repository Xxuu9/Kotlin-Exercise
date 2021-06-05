package com.example.simplepokedex.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class PokemonListWrapper(
    val results: List<PokemonInfo>
)

@JsonClass(generateAdapter = true)
data class PokemonInfo(
    val url: String,
    val name: String
) {
    val id: String get() = url.substringBeforeLast("/").substringAfterLast("/")
    val title: String get() = "$id. ${name.capitalize()}"
}
