package com.example.videogamesapplication.ui.details

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.videogamesapplication.Constants
import com.example.videogamesapplication.R
import com.example.videogamesapplication.model.GameModels
import com.example.videogamesapplication.utils.Utils
import com.example.videogamesapplication.utils.default
import com.example.videogamesapplication.retrofit.RetrofitClient
import com.example.videogamesapplication.retrofit.RetrofitInterface
import com.example.videogamesapplication.room.AppDatabase
import com.example.videogamesapplication.ui.di.qualifers.ActivityContext
import com.example.videogamesapplication.ui.di.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase
) : BaseViewModel() {
    var gameListItemData =
        MutableLiveData<GameModels.GameListModelItemDetails>().default(GameModels.GameListModelItemDetails())

    var isBackButtonClicked = MutableLiveData<Boolean>().default(false)

    var likeButtonClicked = MutableLiveData<Boolean>().default(false)
    var gameListPk = MutableLiveData<String>().default("")

    private val likedGameDao = db.likedGameDao()

    internal fun gatherGameListItemDetails() {
        val coroutineCallGameListItem = CoroutineScope(Dispatchers.IO)
        coroutineCallGameListItem.async {
            RetrofitClient.getRetrofitClient(Constants.VIDEO_GAMES_BASE_URL + Constants.GAMES + "/")
                .create(RetrofitInterface::class.java)
                .getGameListItemDetails(gameListPk.value)
                .enqueue(object : retrofit2.Callback<GameModels.GameListModelItemDetails> {
                    override fun onFailure(
                        call: Call<GameModels.GameListModelItemDetails>,
                        t: Throwable
                    ) {
                        Timber.e("request_failed:${t.cause} || ${t.printStackTrace()}  ")
                        requestFailedAction()
                    }

                    override fun onResponse(
                        call: Call<GameModels.GameListModelItemDetails>,
                        response: Response<GameModels.GameListModelItemDetails>
                    ) {
                        if (Utils.controlResponseCode(
                                responseCode = response.code(),
                                context = context
                            )
                        ) {
                            response.body()?.let { bodyItem ->
                                gameListItemData.postValue(bodyItem)
                                insertLocalDb(bodyItem)
                            } ?: Timber.e("list item body is null !!")
                        } else {
                            requestFailedAction()
                        }
                    }
                })
        }
    }

    private fun requestFailedAction() {
        gatherLocalList()
    }

    fun backButtonClicked() {
        isBackButtonClicked.postValue(true)
    }

    fun likeButtonClicked() {
        likeButtonClicked.postValue(true)
    }

    internal fun insertLikedGamesToDb(gameListModelItem: GameModels.GameListModelItem) {
        likedGameDao.insertGameList(gameListModelItem)
    }

    internal fun removeLikedGamesFromDb(gameListModelItem: GameModels.GameListModelItem) {
        likedGameDao.deleteGameList(gameListModelItem.id)
    }

    internal fun controlGameStateFromDb(): GameModels.GameListModelItem? {
        return if (!gameListPk.value.isNullOrEmpty()) {
            likedGameDao.getGameListItem(gameListPk.value!!.toInt())
        } else {
            null
        }
    }


    private fun controlGameStateDetailsFromDb(): GameModels.GameListModelItemDetails? {
        return if (!gameListPk.value.isNullOrEmpty()) {
            likedGameDao.getGameListDetailsItem(gameListPk.value!!.toInt())
        } else {
            null
        }
    }

    private fun insertGamesDetailsToDb(gameListModelItem: GameModels.GameListModelItemDetails) {
        likedGameDao.insertGameDetails(gameListModelItem)
    }

    private fun removeGamesDetailsFromDb(gameListModelItem: GameModels.GameListModelItemDetails) {
        likedGameDao.deleteGameListDetails(gameListModelItem.id)
    }

    private fun insertLocalDb(gameItem: GameModels.GameListModelItemDetails?) {
        val coroutineCallSaveLocal = CoroutineScope(Dispatchers.IO)
        coroutineCallSaveLocal.async {
            gameItem?.let { item ->
                item.let { details ->
                    val itemDetails = controlGameStateDetailsFromDb()
                    itemDetails?.let {
                        removeGamesDetailsFromDb(it)
                    }
                    insertGamesDetailsToDb(item)
                }
            }
        }
    }


    private fun gatherLocalList() {
        val coroutineCallGatherLocalList = CoroutineScope(Dispatchers.IO)
        coroutineCallGatherLocalList.async {
            val itemData = controlGameStateDetailsFromDb()
            itemData?.let {
                gameListItemData.postValue(it)
                context.let { context ->
                    (context as Activity).runOnUiThread {
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.offine_mode),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } ?: run {
                context.let { context ->
                    (context as Activity).runOnUiThread {
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.offine_mode_empty),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }


}