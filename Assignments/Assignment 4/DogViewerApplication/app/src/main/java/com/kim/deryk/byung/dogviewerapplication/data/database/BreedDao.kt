package com.kim.deryk.byung.dogviewerapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface BreedDao {

    @Query("SELECT * FROM local_breeds")
    fun get(): Flow<List<LocalBreed>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(breeds: List<LocalBreed>)

    @Update
    fun update(breed: LocalBreed)
}