package com.kim.deryk.byung.dogviewerapplication.data.network

import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed
import com.kim.deryk.byung.dogviewerapplication.data.network.models.BreedDetails
import com.kim.deryk.byung.dogviewerapplication.data.network.models.BreedList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("breeds/list/all")
    suspend fun getBreed(): BreedList

    //https://dog.ceo/api/breed/australian/images/random
    @GET("breed/{breed_name}/{sub_breed_name}/images/random")
    suspend fun getDetailsWithBothBreeds(@Path("breed_name") breed: String,
                                         @Path("sub_breed_name") subBreed: String): BreedDetails

    @GET("breed/{breed_name}/images/random")
    suspend fun getDetailsWithBreed(@Path("breed_name") breed: String): BreedDetails
}

