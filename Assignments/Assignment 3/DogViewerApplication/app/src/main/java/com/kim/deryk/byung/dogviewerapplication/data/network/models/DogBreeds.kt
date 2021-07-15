package com.kim.deryk.byung.dogviewerapplication.data.network.models

import com.kim.deryk.byung.dogviewerapplication.data.database.models.LocalBreed
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.util.*
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities

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

@JsonClass(generateAdapter = true)
data class RandomImage(
    @Json(name = "message")
    val url: String
)