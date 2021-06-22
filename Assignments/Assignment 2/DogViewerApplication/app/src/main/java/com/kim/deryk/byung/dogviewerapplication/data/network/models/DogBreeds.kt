package com.kim.deryk.byung.dogviewerapplication.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class BreedList(
    @Json(name = "message")
    val breedsMap: Map<String, List<String>>
) {
    fun getBreeds(): List<Breed> {
        val result = mutableListOf<Breed>()

        for ((breed, subBreeds) in breedsMap) {
            if (subBreeds.isEmpty()) {
                result.add(Breed(breed))
            } else {
                subBreeds.forEach { subBreed ->
                    result.add(Breed(breed, subBreed))
                }
            }
        }

        result.sort()
        return result
    }

}

data class Breed(
    val breed: String,
    val subBreed: String = ""
) : Comparable<Breed> {
    val title: String
        get() = if (subBreed.isNotEmpty())
            "${subBreed.replaceFirstChar { it.titlecase(Locale.CANADA) }} ${breed.replaceFirstChar { it.titlecase(Locale.CANADA) }}"
        else
            breed.replaceFirstChar { it.titlecase(Locale.CANADA) }

    override fun compareTo(other: Breed): Int {
        val compareBreed = breed.compareTo(other.breed)
        return if (compareBreed != 0) compareBreed else title.compareTo(other.title)
    }
}

@JsonClass(generateAdapter = true)
data class BreedDetails(
    @Json(name = "message")
    val url: String,
    val status: String
)