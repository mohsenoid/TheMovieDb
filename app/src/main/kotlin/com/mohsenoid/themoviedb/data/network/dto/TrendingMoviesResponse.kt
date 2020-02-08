package com.mohsenoid.themoviedb.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingMoviesResponse(

    @SerialName(value = "page")
    val page: Int,

    @SerialName(value = "results")
    val movieResults: List<MovieResult>,

    @SerialName(value = "total_pages")
    val totalPages: Int,

    @SerialName(value = "total_results")
    val totalResults: Int
)
