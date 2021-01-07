package com.joenjogu.nexflix

import android.app.Application
import com.joenjogu.nexflix.di.databaseModule
import com.joenjogu.nexflix.di.networkModule
import com.joenjogu.nexflix.di.repositoryModule
import com.joenjogu.nexflix.di.viewModelModule
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