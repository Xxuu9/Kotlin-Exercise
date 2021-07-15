package com.kim.deryk.byung.dogviewerapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kim.deryk.byung.dogviewerapplication.data.database.models.LocalBreed

@Dao
interface BreedDao {
    @Query("SELECT * FROM local_breeds")
    fun get(): LiveData<List<LocalBreed>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(breeds: List<LocalBreed>)

    @Update
    fun update(breed: LocalBreed)
}