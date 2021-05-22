package com.example.simplepokedex.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


    @JsonClass(generateAdapter = true)
    data class PokemonListWrapper(
        val results: List<PokemonInfo>
    )

    @JsonClass(generateAdapter = true)
    data class PokemonInfo(
        val url: String,
        val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Course(
        val code: String,
        val name: String,
        val crn: Int,
        @Json(name = "schedule_info")
        val scheduleInfo: Map<String, String>,
        val assignments: List<String>
    )
