package com.example.dogbreed.network.models

import com.squareup.moshi.JsonClass

/*
Json:
{"message":
            {"affenpinscher":[],
            ...
            "mastiff":["bull","english","tibetan"],
            ...
            },
 "status": "success"
}
*/

//Convert Json to Object
@JsonClass(generateAdapter = true)
data class BreedList(
    val message: Map<String, List<String>>,
    val status: String
){
    fun getBreeds(): List<Breed>{
        val result = mutableListOf<Breed>()
        // key is Breed.breed
        // message[key] (or value) is List<Breed.subBreed>
        for(key in message.keys) {
            if(message[key].isNullOrEmpty()){
                var bd = Breed(key,"")
                result.add(bd)
            }
            else{
                for(elem in message[key]!!){
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
