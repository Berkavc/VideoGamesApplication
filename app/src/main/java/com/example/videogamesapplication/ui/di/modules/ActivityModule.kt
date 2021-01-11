package com.example.videogamesapplication.ui.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.videogamesapplication.room.AppDatabase
import com.example.videogamesapplication.ui.di.qualifers.ActivityContext
import com.example.videogamesapplication.ui.di.scopes.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    @PerActivity
    @ActivityContext
    internal fun provideActivityContext(): Context {
        return mActivity
    }

    @Provides
    @PerActivity
    internal fun provideRoomDbBuilder(): AppDatabase {
        val db: AppDatabase = Room.databaseBuilder(
            provideActivityContext(),
            AppDatabase::class.java, "GameList.db"
        ).fallbackToDestructiveMigration().build()
        return db
    }

}
