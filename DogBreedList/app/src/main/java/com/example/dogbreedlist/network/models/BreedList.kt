package com.example.dogbreedlist.network.models

import android.util.Log
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class BreedList(
    val message: Map<String, List<String>>,
    val status: String
){
    fun getBreeds(): List<Breed> {
//        val breedList: List<String> get() = message.keys.toList()
        val result = mutableListOf<Breed>()

        for (key in message.keys) {
            if (message[key].isNullOrEmpty()){
                var bd = Breed(key, "")
                result.add(bd)
            }
            else {
                for (elem in message[key]!!) {
                    var bd = Breed(key, elem)
                    result.add(bd)
                }
            }
        }
        //result.add(Breed("apple"))
        result.sort()
        //result.add(Breed("apple2"))

        return result
    }
}

