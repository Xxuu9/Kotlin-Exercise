package com.kim.deryk.byung.dogviewerapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kim.deryk.byung.dogviewerapplication.data.database.models.LocalBreed


@Database(entities = [LocalBreed::class], version = 2)
abstract class BreedDatabase: RoomDatabase() {
    abstract fun getBreedDao(): BreedDao
}
