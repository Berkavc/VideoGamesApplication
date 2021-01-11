package com.example.videogamesapplication.retrofit

import com.example.videogamesapplication.Constants
import com.example.videogamesapplication.model.GameModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET(Constants.GAMES)
    fun getGameList(): Call<GameModels.GameListModel>

    @GET("{${Constants.GAME_PK}}")
    fun getGameListItemDetails(@Path(Constants.GAME_PK) gamePk: String?): Call<GameModels.GameListModelItemDetails>
}