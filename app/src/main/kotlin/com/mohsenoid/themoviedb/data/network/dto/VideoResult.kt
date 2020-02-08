package com.mohsenoid.themoviedb.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResult(

    @SerialName(value = "id")
    val id: String,

    @SerialName(value = "name")
    val name: String,

    @SerialName(value = "site")
    val site: String,

    @SerialName(value = "type")
    val type: String,

    @SerialName(value = "key")
    val key: String
)
