package com.kim.deryk.byung.dogviewerapplication.data.network

import com.kim.deryk.byung.dogviewerapplication.data.network.models.BreedList
import com.kim.deryk.byung.dogviewerapplication.data.network.models.RandomImage
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("breeds/list/all")
    suspend fun getBreed(): BreedList

    @GET("breed/{breed_name}/{sub_breed_name}/images/random")
    suspend fun getRandomImage(@Path("breed_name") breedName: String,
                               @Path("sub_breed_name") subBreedName: String): RandomImage

    @GET("breed/{breed_name}/images/random")
    suspend fun getRandomImage(@Path("breed_name") breedName: String): RandomImage
}