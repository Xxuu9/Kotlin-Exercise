package com.example.dogbreed.network.models

import android.util.Log
import java.util.*

data class Breed(
    val breed: String,
    val subBreed: String = ""
): Comparable<Breed>{
    /*
    breed = "australian"
    subBreed = "shepherd"
    => title = "Shepherd Australian"

    breed = "australian"
    subBreed = ""
    => title = "Australian"
     */
    val title: String get() = if (subBreed != "") "${subBreed.capitalize()} ${breed.capitalize()}" else "${breed.capitalize()}"

    override fun compareTo(other: Breed): Int {
        /*
        compareBreed is an integer.
        If compareBreed > 0, it means other.breed should be in front of breed.
        Otherwise, breed should be in front of other.breed
        Example:
        $breed  ${other.breed} $compareBreed
        african affenpinscher  12
        pointer pointer        0
        apple beagle           -1
         */
        val compareBreed = breed.compareTo(other.breed)
        // Log.e("txx", "$breed ${other.breed} $compareBreed")
        return if (compareBreed != 0) compareBreed else title.compareTo(other.title)
    }
}
