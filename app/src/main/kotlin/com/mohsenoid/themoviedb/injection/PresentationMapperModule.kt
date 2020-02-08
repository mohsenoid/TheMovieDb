package com.mohsenoid.themoviedb.injection

import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import com.mohsenoid.themoviedb.presentation.mapper.MovieModelMapper
import com.mohsenoid.themoviedb.presentation.mapper.TrailerModelMapper
import com.mohsenoid.themoviedb.presentation.mapper.TvShowModelMapper
import com.mohsenoid.themoviedb.presentation.model.MovieModel
import com.mohsenoid.themoviedb.presentation.model.TrailerModel
import com.mohsenoid.themoviedb.presentation.model.TvShowModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationMapperModule = module {

    single<Mapper<MovieEntity, MovieModel>>(named<MovieModelMapper>()) { MovieModelMapper() }

    single<Mapper<TvShowEntity, TvShowModel>>(named<TvShowModelMapper>()) { TvShowModelMapper() }

    single<Mapper<TrailerEntity, TrailerModel>>(named<TrailerModelMapper>()) { TrailerModelMapper() }
}
