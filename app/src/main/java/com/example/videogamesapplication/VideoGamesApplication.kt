package com.example.videogamesapplication

import android.app.Application
import androidx.databinding.library.BuildConfig
import com.example.videogamesapplication.ui.di.components.AppComponent
import com.example.videogamesapplication.ui.di.components.DaggerAppComponent
import com.example.videogamesapplication.ui.di.modules.AppModule
import timber.log.Timber

class VideoGamesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (com.example.videogamesapplication.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}