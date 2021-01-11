package com.example.videogamesapplication.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.videogamesapplication.VideoGamesApplication
import com.example.videogamesapplication.ui.di.components.ActivityComponent
import com.example.videogamesapplication.ui.di.components.DaggerActivityComponent
import com.example.videogamesapplication.ui.di.modules.ActivityModule
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var mActivityComponent: ActivityComponent? = null
    fun activityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(VideoGamesApplication.appComponent)
                .build()
        }
        return mActivityComponent!!

    }

    override fun onBackPressed() {
        finish()
    }
}