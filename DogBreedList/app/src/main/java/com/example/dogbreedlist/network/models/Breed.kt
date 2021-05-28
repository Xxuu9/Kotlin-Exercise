package com.example.dogbreedlist.network.models

import android.util.Log
import java.util.*

data class Breed (
        var breed: String,
        var subBreed: String = ""
) : Comparable<Breed> {
    val title: String get() = if (subBreed != "") "${subBreed.capitalize()} ${breed.capitalize()}" else "${breed.capitalize()}"

    override fun compareTo(other: Breed): Int {
        val compareBreed = breed.compareTo(other.breed)
//      Log.e("txx", "$breed ${other.breed} $compareBreed")
        return if (compareBreed != 0) compareBreed else title.compareTo(other.title)
    }
}