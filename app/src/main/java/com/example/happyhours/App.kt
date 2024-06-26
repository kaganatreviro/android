package com.example.happyhours

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.data.di.dataModule
import com.example.presentation.di.authModule
import com.example.presentation.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidContext(this@App)
            modules(authModule, mainModule, dataModule)
        }
    }
}