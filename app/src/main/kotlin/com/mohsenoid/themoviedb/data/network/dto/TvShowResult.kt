package com.mohsenoid.themoviedb.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowResult(

    @SerialName(value = "id")
    val id: Int,

    @SerialName(value = "name")
    val name: String,

    @SerialName(value = "first_air_date")
    val firstAirDate: String,

    @SerialName(value = "overview")
    val overview: String,

    @SerialName(value = "poster_path")
    val posterPath: String,

    @SerialName(value = "vote_average")
    val voteAverage: Double
)
