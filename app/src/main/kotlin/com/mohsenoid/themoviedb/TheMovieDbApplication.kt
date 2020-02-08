package com.mohsenoid.themoviedb

import android.app.Application
import com.mohsenoid.themoviedb.injection.appModule
import com.mohsenoid.themoviedb.injection.dataModule
import com.mohsenoid.themoviedb.injection.presentationModule
import com.mohsenoid.themoviedb.injection.qualifier.QualifierName
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import timber.log.Timber

class TheMovieDbApplication : Application(), KoinComponent {

    private val isDebug: Boolean = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()

        if (isDebug) setupTimber()

        startKoin {
            val appProperties: HashMap<String, Any> = hashMapOf(
                QualifierName.IS_DEBUG to isDebug,
                QualifierName.BASE_URL to BuildConfig.BASE_URL,
                QualifierName.API_KEY to BuildConfig.API_KEY
            )
            properties(appProperties)

            if (isDebug) androidLogger()

            androidContext(this@TheMovieDbApplication)
            modules(appModule + dataModule + presentationModule)
        }
    }

    private fun setupTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                // adding file name and line number link to logs
                return "${super.createStackElementTag(element)}(${element.fileName}:${element.lineNumber})"
            }
        })
    }
}
