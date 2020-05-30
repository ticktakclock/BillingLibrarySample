package com.github.ticktakclock.billinglibrarysample

import android.app.Application
import com.github.ticktakclock.billinglibrarysample.di.MODULE_MAIN
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(MODULE_MAIN)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}