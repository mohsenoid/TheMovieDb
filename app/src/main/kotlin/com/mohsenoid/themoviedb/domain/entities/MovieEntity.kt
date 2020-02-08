package com.mohsenoid.themoviedb.domain.entities

data class MovieEntity(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
    val posterUrl: String
)
