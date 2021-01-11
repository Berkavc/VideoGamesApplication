package com.example.videogamesapplication.ui.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.videogamesapplication.ui.details.DetailsViewModel
import com.example.videogamesapplication.ui.di.viewmodel.ViewModelFactory
import com.example.videogamesapplication.ui.di.viewmodel.ViewModelKey
import com.example.videogamesapplication.ui.home.HomeViewModel
import com.example.videogamesapplication.ui.like.LikeViewModel
import com.example.videogamesapplication.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindsDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LikeViewModel::class)
    abstract fun bindsLikeViewModel(likeViewModel: LikeViewModel): ViewModel

}