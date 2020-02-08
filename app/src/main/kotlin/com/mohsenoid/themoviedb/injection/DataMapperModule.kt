package com.mohsenoid.themoviedb.injection

import com.mohsenoid.themoviedb.data.mapper.MovieEntityMapper
import com.mohsenoid.themoviedb.data.mapper.TvShowEntityMapper
import com.mohsenoid.themoviedb.data.mapper.TrailerEntityMapper
import com.mohsenoid.themoviedb.data.network.dto.MovieResult
import com.mohsenoid.themoviedb.data.network.dto.TvShowResult
import com.mohsenoid.themoviedb.data.network.dto.VideoResult
import com.mohsenoid.themoviedb.domain.entities.MovieEntity
import com.mohsenoid.themoviedb.domain.entities.TrailerEntity
import com.mohsenoid.themoviedb.domain.entities.TvShowEntity
import com.mohsenoid.themoviedb.domain.mapper.Mapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataMapperModule = module {

    single<Mapper<MovieResult, MovieEntity>>(named<MovieEntityMapper>()) { MovieEntityMapper() }

    single<Mapper<TvShowResult, TvShowEntity>>(named<TvShowEntityMapper>()) { TvShowEntityMapper() }

    single<Mapper<VideoResult, TrailerEntity>>(named<TrailerEntityMapper>()) { TrailerEntityMapper() }
}
