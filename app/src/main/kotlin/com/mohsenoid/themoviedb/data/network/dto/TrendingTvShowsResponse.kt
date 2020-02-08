package com.mohsenoid.themoviedb.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingTvShowsResponse(

    @SerialName(value = "page")
    val page: Int,

    @SerialName(value = "results")
    val tvShowResults: List<TvShowResult>,

    @SerialName(value = "total_pages")
    val totalPages: Int,

    @SerialName(value = "total_results")
    val totalResults: Int
)
