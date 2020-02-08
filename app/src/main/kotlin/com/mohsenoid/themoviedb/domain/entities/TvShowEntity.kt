package com.mohsenoid.themoviedb.domain.entities

data class TvShowEntity(
    val id: Int,
    val name: String,
    val firstAirDate: String,
    val overview: String,
    val voteAverage: Double,
    val posterUrl: String
)
