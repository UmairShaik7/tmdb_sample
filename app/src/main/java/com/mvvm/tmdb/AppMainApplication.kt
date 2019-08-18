package com.mvvm.tmdb

import android.app.Application
import com.mvvm.data.repoModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppMainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidContext(this@AppMainApplication)
            androidLogger()
            modules(listOf(appModule, repoModule))
        }
    }
}