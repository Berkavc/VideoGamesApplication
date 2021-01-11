package com.example.videogamesapplication.ui.like

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.videogamesapplication.model.GameModels
import com.example.videogamesapplication.room.AppDatabase
import com.example.videogamesapplication.ui.di.qualifers.ActivityContext
import com.example.videogamesapplication.ui.di.viewmodel.BaseViewModel
import com.example.videogamesapplication.utils.default
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class LikeViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase
) : BaseViewModel() {
    var gameList =
        MutableLiveData<MutableList<GameModels.GameListModelItem?>>().default(mutableListOf())

    var controlUIVisibilities = MutableLiveData<Boolean>().default(false)

    private val likedGameDao = db.likedGameDao()
    internal fun gatherLikedGameList() {
        val coroutineCallLikedGames = CoroutineScope(Dispatchers.IO)
        coroutineCallLikedGames.async {
            gameList.postValue(likedGameDao.getAllLikedItems()?.toMutableList())
        }
    }
}