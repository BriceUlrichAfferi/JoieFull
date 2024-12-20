package com.example.joiefull


import android.app.Application
import com.example.joiefull.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JoieFullApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@JoieFullApplication)
            modules(appModule)
        }
    }
}
