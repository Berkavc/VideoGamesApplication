package com.example.videogamesapplication.ui.di.components

import android.content.Context
import com.example.videogamesapplication.ui.di.modules.AppModule
import com.example.videogamesapplication.ui.di.qualifers.AppContext
import com.example.videogamesapplication.ui.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    @AppContext
    fun appContext(): Context

}
