package com.example.videogamesapplication.ui.di.components

import android.content.Context
import com.example.videogamesapplication.room.AppDatabase
import com.example.videogamesapplication.ui.details.DetailsActivity
import com.example.videogamesapplication.ui.di.modules.ActivityModule
import com.example.videogamesapplication.ui.di.qualifers.ActivityContext
import com.example.videogamesapplication.ui.di.scopes.PerActivity
import com.example.videogamesapplication.ui.di.viewmodel.ViewModelModule
import com.example.videogamesapplication.ui.main.MainActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, ViewModelModule::class]
)
interface ActivityComponent : AppComponent {

    @ActivityContext
    fun activityContext(): Context
    fun provideRoomDbBuilder(): AppDatabase
    fun inject(activity: MainActivity)
    fun inject(activity: DetailsActivity)


}
