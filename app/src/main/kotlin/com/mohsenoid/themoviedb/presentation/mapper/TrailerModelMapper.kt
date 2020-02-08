package com.mohsenoid.themoviedb.presentation.mapper

import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.model.TrailerModel

class TrailerModelMapper : Mapper<TrailerEntity, TrailerModel> {

    override fun map(input: TrailerEntity): TrailerModel {
        return TrailerModel(
            name = input.name,
            url = input.url
        )
    }
}
