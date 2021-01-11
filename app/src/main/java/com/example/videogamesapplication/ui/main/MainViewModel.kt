package com.example.videogamesapplication.ui.main

import android.content.Context
import com.example.videogamesapplication.room.AppDatabase
import com.example.videogamesapplication.ui.di.qualifers.ActivityContext
import com.example.videogamesapplication.ui.di.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase
) : BaseViewModel() {

}