package com.joenjogu.nexflix

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class NexflixApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NexflixApplication)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}