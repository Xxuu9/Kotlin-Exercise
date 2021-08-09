package com.kim.deryk.byung.dogviewerapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed

@Entity(tableName = "local_breeds")
data class LocalBreed(
    @PrimaryKey
    val title: String,
    val breed: String,
    val subBreed: String,
    val isFaved: Boolean
) {
    fun toBreed(): Breed {
        return Breed(
            breed = breed,
            subBreed = subBreed,
            isFaved = isFaved
        )
    }
}