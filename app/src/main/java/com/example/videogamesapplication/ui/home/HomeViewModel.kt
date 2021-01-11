package com.example.videogamesapplication.ui.home

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
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

class HomeViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase
) : BaseViewModel() {
    var gameList =
        MutableLiveData<MutableList<GameModels.GameListResultModel?>>().default(mutableListOf())

    var controlUIVisibilities = MutableLiveData<Boolean>().default(false)

    var controlReloadButtonVisibility = MutableLiveData<Boolean>().default(false)

    var controlProgressBarVisibility = MutableLiveData<Boolean>().default(false)

    var controlViewPagerVisibility = MutableLiveData<Boolean>()

    var controlRecyclerViewVisibility = MutableLiveData<Boolean>()

    private val likedGameDao = db.likedGameDao()

    internal fun gatherGameList() {
        controlReloadButtonVisibility.postValue(false)
        controlProgressBarVisibility.postValue(true)
        val coroutineCallGameList = CoroutineScope(Dispatchers.IO)
        coroutineCallGameList.async {
            RetrofitClient.getRetrofitClient(Constants.VIDEO_GAMES_BASE_URL)
                .create(RetrofitInterface::class.java)
                .getGameList().enqueue(object : retrofit2.Callback<GameModels.GameListModel> {
                    override fun onFailure(
                        call: Call<GameModels.GameListModel>,
                        t: Throwable
                    ) {
                        requestFailedAction()
                    }

                    override fun onResponse(
                        call: Call<GameModels.GameListModel>,
                        response: Response<GameModels.GameListModel>
                    ) {
                        controlProgressBarVisibility.postValue(false)
                        if (Utils.controlResponseCode(
                                responseCode = response.code(),
                                context = context
                            )
                        ) {
                            response.body()?.let { body ->
                                body.results.toMutableList().let { resultList ->
                                    resultList.let {
                                        gameList.postValue(it)
                                        insertLocalDb(it)
                                    }
                                }
                            } ?: Timber.e("list body is null !!")
                        } else {
                            requestFailedAction()
                        }
                    }
                })
        }
    }

    private fun requestFailedAction() {
        controlProgressBarVisibility.postValue(false)
        Timber.e("request failed gathering from local")
        gatherLocalList()
    }

    private fun gatherLocalList() {
        val coroutineCallGatherLocalList = CoroutineScope(Dispatchers.IO)
        coroutineCallGatherLocalList.async {
            likedGameDao.getAllResultItems()?.let {
                if (it.isNotEmpty()) {
                    gameList.postValue(it.toMutableList())
                    context.let { context ->
                        (context as Activity).runOnUiThread {
                            Toast.makeText(
                                context,
                                context.resources.getString(R.string.offine_mode),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    reloadAction()
                }
            } ?: run {
                reloadAction()
            }

        }
    }

    private fun reloadAction() {
        context.let { context ->
            (context as Activity).runOnUiThread {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.offine_mode_empty),
                    Toast.LENGTH_SHORT
                ).show()
                controlReloadButtonVisibility.postValue(true)
            }
        }

    }

    private fun insertLocalDb(mutableListGameResults: MutableList<GameModels.GameListResultModel?>) {
        val coroutineCallSaveLocal = CoroutineScope(Dispatchers.IO)
        coroutineCallSaveLocal.async {
            likedGameDao.getAllResultItems()?.forEach {
                it?.let {
                    removeResultsFromDb(it)
                }
            }
            mutableListGameResults.forEach {
                it?.let {
                    insertResultsToDb(it)
                }
            }
        }
    }

    private fun insertResultsToDb(gameListResult: GameModels.GameListResultModel) {
        likedGameDao.insertGameResults(gameListResult)
    }

    private fun removeResultsFromDb(gameListResult: GameModels.GameListResultModel) {
        likedGameDao.deleteGameResult(gameListResult.id)
    }

    fun reloadButtonClicked() {
        gatherGameList()
    }
}