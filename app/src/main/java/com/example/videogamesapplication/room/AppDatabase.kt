package com.example.videogamesapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.videogamesapplication.model.GameModels

@Database(
    entities = [GameModels.GameListModel::class, GameModels.GameListResultModel::class, GameModels.GameListResultRatingsModel::class, GameModels.GameListResultAddedByStatus::class, GameModels.GameListResultPlatforms::class, GameModels.GameListResultPlatformsPlatform::class, GameModels.GameListResultsPlatformsRequirementsEn::class, GameModels.GameListResultsPlatformsRequirementsRu::class, GameModels.GameListResultsParentPlatforms::class, GameModels.GameListResultParentPlatformsPlatform::class, GameModels.GameListResultGenres::class, GameModels.GameListResultStores::class, GameModels.GameListResultStoresStore::class, GameModels.GameListResultClip::class, GameModels.GameListResultTags::class, GameModels.GameListResultEsrbRating::class, GameModels.GameListResultShortScreenshots::class, GameModels.GameListModelItem::class, GameModels.GameListModelItemDetails::class, GameModels.GameListModelItemMetacriticPlatforms::class, GameModels.GameListModelItemMetacriticPlatformsPlatform::class, GameModels.GameListModelItemRatings::class, GameModels.GameListModelItemAddedByStatus::class, GameModels.GameListModelItemDevelopers::class, GameModels.GameListModelItemPublishers::class],
    version = 12,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun likedGameDao(): LikedGameDao
}
