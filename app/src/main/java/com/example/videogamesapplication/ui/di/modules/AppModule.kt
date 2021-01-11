package com.example.videogamesapplication.ui.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.example.videogamesapplication.ui.di.qualifers.AppContext
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApp: Application) {

    @Provides
    @Singleton
    internal fun provideApplication() = mApp

    @Provides
    @Singleton
    @AppContext
    internal fun provideAppContext(): Context = mApp

}