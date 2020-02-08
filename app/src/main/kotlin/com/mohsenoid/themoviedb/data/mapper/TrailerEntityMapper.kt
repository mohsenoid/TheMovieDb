package com.mohsenoid.themoviedb.data.mapper

import com.mohsenoid.themoviedb.data.network.NetworkConstants
import com.mohsenoid.themoviedb.data.network.dto.VideoResult
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper

class TrailerEntityMapper : Mapper<VideoResult, TrailerEntity> {

    override fun map(input: VideoResult): TrailerEntity {
        return TrailerEntity(
            id = input.id,
            name = input.name,
            url = "${NetworkConstants.YOUTUBE_URL_BASE_URL}${input.key}"
        )
    }
}
