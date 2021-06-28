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

@JsonClass(generateAdapter = true)
data class PokemonDetails(
    val id: Int,
    val name: String,
    @Json(name = "base_experience")
    val baseExperience: Int,
    val height: Double,
    val weight: Double,
    val sprites: PokemonSprite,
    val types: List<PokemonTypeWrapper>
){
    val title: String get() = "$id. ${name.capitalize()}"
    val baseExpString: String get() = baseExperience.toString()
    val formattedWeight: String get() = String.format("%.1f kg", weight / 10)
    val formattedHeight: String get() = String.format("%.1f m", height / 10)
    val spriteUrl: String get() = sprites.imageUrl
    val browsePageUrl: String get() = "https://pokemon.fandom.com/wiki/$name"
}

@JsonClass(generateAdapter = true)
data class PokemonSprite(
    @Json(name = "front_default")
    val imageUrl: String
)

@JsonClass(generateAdapter = true)
data class PokemonTypeWrapper(
    val type: PokemonType
) {
    val typeString: String get() = type.name
}

@JsonClass(generateAdapter = true)
data class PokemonType(
    val name: String
)