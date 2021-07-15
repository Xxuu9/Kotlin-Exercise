package com.kim.deryk.byung.dogviewerapplication.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed


@Entity(tableName = "local_breeds")
data class LocalBreed(
    @PrimaryKey
    val title: String,
    val breed: String,
    val subBreed: String,
    val isFave: Boolean = false
) {
    fun toDomain(): Breed {
        return Breed(
            breed,
            subBreed,
            isFave
        )
    }
}
