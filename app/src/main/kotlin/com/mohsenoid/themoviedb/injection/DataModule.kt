package com.mohsenoid.themoviedb.injection

import com.mohsenoid.themoviedb.data.RepositoryImpl
import com.mohsenoid.themoviedb.data.mapper.MovieEntityMapper
import com.mohsenoid.themoviedb.data.mapper.TvShowEntityMapper
import com.mohsenoid.themoviedb.data.mapper.TrailerEntityMapper
import com.mohsenoid.themoviedb.domain.Repository
import com.mohsenoid.themoviedb.injection.qualifier.QualifierName
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = dataMapperModule + dataNetworkModule + module {

    single<Repository> {
        RepositoryImpl(
            apiKey = getProperty(QualifierName.API_KEY),
            networkClient = get(),
            ioDispatcher = get(named(QualifierName.IO_DISPATCHER)),
            movieEntityMapper = get(named<MovieEntityMapper>()),
            tvShowEntityMapper = get(named<TvShowEntityMapper>()),
            trailerEntityMapper = get(named<TrailerEntityMapper>())
        )
    }
}
