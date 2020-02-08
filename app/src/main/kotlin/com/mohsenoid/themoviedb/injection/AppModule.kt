package com.mohsenoid.themoviedb.injection

import com.mohsenoid.rickandmorty.util.dispatcher.AppDispatcherProvider
import com.mohsenoid.rickandmorty.util.dispatcher.DispatcherProvider
import com.mohsenoid.themoviedb.injection.qualifier.QualifierName
import com.mohsenoid.themoviedb.util.config.ConfigProvider
import com.mohsenoid.themoviedb.util.config.ConfigProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single<ConfigProvider> { ConfigProviderImpl(context = androidContext()) }

    single<DispatcherProvider> { AppDispatcherProvider() }

    single(named(QualifierName.MAIN_DISPATCHER)) { get<DispatcherProvider>().mainDispatcher }

    single(named(QualifierName.IO_DISPATCHER)) { get<DispatcherProvider>().ioDispatcher }

    single(named(QualifierName.CACHE_DIR)) { androidContext().cacheDir }
}
