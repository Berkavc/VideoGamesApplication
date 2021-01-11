package com.example.videogamesapplication.room

import androidx.room.*
import com.example.videogamesapplication.model.GameModels

@Dao
interface LikedGameDao {
    @Query("SELECT * FROM GameListModelItem")
    fun getAllLikedItems(): List<GameModels.GameListModelItem?>?

    @Query("SELECT * FROM GameListResult")
    fun getAllResultItems(): List<GameModels.GameListResultModel?>?

    @Query("SELECT * FROM GameListModelItem WHERE id = :itemId ")
    fun getGameListItem(itemId: Int?): GameModels.GameListModelItem?

    @Query("SELECT * FROM GameListModelItemDetails WHERE id = :itemId ")
    fun getGameListDetailsItem(itemId: Int?): GameModels.GameListModelItemDetails?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameList(gameList: GameModels.GameListModelItem?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameDetails(gameList: GameModels.GameListModelItemDetails?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameResults(gameList: GameModels.GameListResultModel?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(gameList: MutableList<GameModels.GameListModelItem?>)

    @Query("DELETE FROM GameListModelItem WHERE id = :itemId")
    fun deleteGameList(itemId: Int?)

    @Query("DELETE FROM GameListModelItemDetails WHERE id = :itemId")
    fun deleteGameListDetails(itemId: Int?)

    @Query("DELETE FROM GameListResult WHERE id = :itemId")
    fun deleteGameResult(itemId: Int?)

}