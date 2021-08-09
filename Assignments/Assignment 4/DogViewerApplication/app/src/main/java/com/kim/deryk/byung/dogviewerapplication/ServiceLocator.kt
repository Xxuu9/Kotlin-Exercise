package com.kim.deryk.byung.dogviewerapplication

import android.content.Context
import androidx.room.Room
import com.kim.deryk.byung.dogviewerapplication.data.database.BreedDatabase
import com.kim.deryk.byung.dogviewerapplication.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator(applicationContext: Context) {
    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"
        private const val DB_NAME = "breeds"
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

    val database = Room.databaseBuilder(applicationContext, BreedDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
}