package com.joenjogu.nexflix.utilities

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class KoinTestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinTestApp)
            module { emptyList<Module>() }
        }
    }

    internal fun injectModule(module: Module) {
        loadKoinModules(module)
    }
}
