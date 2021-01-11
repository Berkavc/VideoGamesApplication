package com.example.videogamesapplication.ui.di.components

import com.example.videogamesapplication.ui.di.modules.FragmentModule
import com.example.videogamesapplication.ui.di.scopes.PerActivity
import com.example.videogamesapplication.ui.di.scopes.PerFragment
import com.example.videogamesapplication.ui.di.viewmodel.ViewModelModule
import com.example.videogamesapplication.ui.home.HomeFragment
import com.example.videogamesapplication.ui.like.LikeFragment
import dagger.Component

@PerFragment
@Component(
    dependencies = [ActivityComponent::class],
    modules = [FragmentModule::class,
        ViewModelModule::class]
)
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: LikeFragment)

}
