package com.kim.deryk.byung.dogviewerapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalBreed::class], version = 1)
abstract class BreedDatabase: RoomDatabase() {
    abstract fun getBreedDao(): BreedDao
}