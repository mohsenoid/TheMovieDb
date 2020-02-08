package com.mohsenoid.themoviedb.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResult(

    @SerialName(value = "id")
    val id: Int,

    @SerialName(value = "title")
    val title: String,

    @SerialName(value = "release_date")
    val releaseDate: String,

    @SerialName(value = "overview")
    val overview: String,

    @SerialName(value = "poster_path")
    val posterPath: String,

    @SerialName(value = "vote_average")
    val voteAverage: Double
)
