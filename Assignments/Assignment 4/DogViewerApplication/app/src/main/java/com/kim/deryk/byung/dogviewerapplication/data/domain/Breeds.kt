package com.kim.deryk.byung.dogviewerapplication.data.domain

import com.kim.deryk.byung.dogviewerapplication.data.database.LocalBreed
import java.io.Serializable
import java.util.*

data class Breed(
    val breed: String,
    val subBreed: String = "",
    var isFaved: Boolean = false
) : Comparable<Breed>, Serializable {
    val title: String
        get() = if (subBreed.isNotEmpty())
            "${subBreed.replaceFirstChar { it.titlecase(Locale.CANADA) }} ${breed.replaceFirstChar { it.titlecase(Locale.CANADA) }}"
        else
            breed.replaceFirstChar { it.titlecase(Locale.CANADA) }

    override fun compareTo(other: Breed): Int {
        val compareBreed = breed.compareTo(other.breed)
        return if (compareBreed != 0) compareBreed else title.compareTo(other.title)
    }

    fun toLocalBreed(): LocalBreed {
        return LocalBreed(
            title = title,
            breed = breed,
            subBreed = subBreed,
            isFaved = isFaved
        )
    }
}