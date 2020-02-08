package com.mohsenoid.themoviedb.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(

    @SerialName(value = "id")
    val id: Int,

    @SerialName(value = "results")
    val results: List<VideoResult>
)
