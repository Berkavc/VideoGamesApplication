package com.example.videogamesapplication.room

import androidx.room.TypeConverter
import com.example.videogamesapplication.model.GameModels
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class RoomConverters {
    private val gson = Gson()

    @TypeConverter
    fun stringToGameListModel(input: String?): GameModels.GameListModel? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListModel::class.java)
        }
    }

    @TypeConverter
    fun gameListModelToString(input: GameModels.GameListModel?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultModel(input: String?): GameModels.GameListResultModel? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultModel::class.java)
        }
    }

    @TypeConverter
    fun gameListResultModelToString(input: GameModels.GameListResultModel?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultRatingsModel(input: String?): GameModels.GameListResultRatingsModel? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultRatingsModel::class.java)
        }
    }

    @TypeConverter
    fun gameListResultRatingsModelToString(input: GameModels.GameListResultRatingsModel?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultAddedByStatusModel(input: String?): GameModels.GameListResultAddedByStatus? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultAddedByStatus::class.java)
        }
    }

    @TypeConverter
    fun gameListResultAddedByStatusModelToString(input: GameModels.GameListResultAddedByStatus?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultPlatforms(input: String?): GameModels.GameListResultPlatforms? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultPlatforms::class.java)
        }
    }

    @TypeConverter
    fun gameListResultPlatformsToString(input: GameModels.GameListResultPlatforms?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultPlatformsPlatform(input: String?): GameModels.GameListResultPlatformsPlatform? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultPlatformsPlatform::class.java)
        }
    }

    @TypeConverter
    fun gameListResultPlatformsPlatformToString(input: GameModels.GameListResultPlatformsPlatform?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultPlatformsRequirementsEn(input: String?): GameModels.GameListResultsPlatformsRequirementsEn? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultsPlatformsRequirementsEn::class.java)
        }
    }

    @TypeConverter
    fun gameListResultPlatformsRequirementsEnToString(input: GameModels.GameListResultsPlatformsRequirementsEn?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultPlatformsRequirementsRu(input: String?): GameModels.GameListResultsPlatformsRequirementsRu? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultsPlatformsRequirementsRu::class.java)
        }
    }

    @TypeConverter
    fun gameListResultPlatformsRequirementsRuToString(input: GameModels.GameListResultsPlatformsRequirementsRu?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun gameListResultsParentPlatformsToString(input: GameModels.GameListResultsParentPlatforms?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultsParentPlatforms(input: String?): GameModels.GameListResultsParentPlatforms? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultsParentPlatforms::class.java)
        }
    }

    @TypeConverter
    fun gameListResultParentPlatformsPlatformToString(input: GameModels.GameListResultParentPlatformsPlatform?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultParentPlatformsPlatform(input: String?): GameModels.GameListResultParentPlatformsPlatform? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultParentPlatformsPlatform::class.java)
        }
    }

    @TypeConverter
    fun gameListResultGenresToString(input: GameModels.GameListResultGenres?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultGenres(input: String?): GameModels.GameListResultGenres? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultGenres::class.java)
        }
    }

    @TypeConverter
    fun gameListResultStoresToString(input: GameModels.GameListResultStores?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultStores(input: String?): GameModels.GameListResultStores? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultStores::class.java)
        }
    }

    @TypeConverter
    fun gameListResultStoresStoreToString(input: GameModels.GameListResultStoresStore?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultStoresStore(input: String?): GameModels.GameListResultStoresStore? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultStoresStore::class.java)
        }
    }

    @TypeConverter
    fun gameListResultClipToString(input: GameModels.GameListResultClip?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultClip(input: String?): GameModels.GameListResultClip? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultClip::class.java)
        }
    }

    @TypeConverter
    fun gameListResultTagsToString(input: GameModels.GameListResultTags?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultTags(input: String?): GameModels.GameListResultTags? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultTags::class.java)
        }
    }


    @TypeConverter
    fun gameListResultEsrbRatingToString(input: GameModels.GameListResultEsrbRating?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultEsrbRating(input: String?): GameModels.GameListResultEsrbRating? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultEsrbRating::class.java)
        }
    }

    @TypeConverter
    fun gameListResultShortScreenshotsToString(input: GameModels.GameListResultShortScreenshots?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListResultShortScreenshots(input: String?): GameModels.GameListResultShortScreenshots? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListResultShortScreenshots::class.java)
        }
    }

    @TypeConverter
    fun gameListModelItemToString(input: GameModels.GameListModelItem?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListModelItem(input: String?): GameModels.GameListModelItem? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListModelItem::class.java)
        }
    }

    @TypeConverter
    fun gameListModelItemMetacriticPlatformsToString(input: GameModels.GameListModelItemMetacriticPlatforms?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListModelItemMetacriticPlatforms(input: String?): GameModels.GameListModelItemMetacriticPlatforms? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListModelItemMetacriticPlatforms::class.java)
        }
    }

    @TypeConverter
    fun gameListModelItemMetacriticPlatformsPlatformToString(input: GameModels.GameListModelItemMetacriticPlatformsPlatform?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListModelItemMetacriticPlatformsPlatform(input: String?): GameModels.GameListModelItemMetacriticPlatformsPlatform? {
        return input?.let {
            gson.fromJson(
                it,
                GameModels.GameListModelItemMetacriticPlatformsPlatform::class.java
            )
        }
    }

    @TypeConverter
    fun gameListModelItemRatingsToString(input: GameModels.GameListModelItemRatings?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListModelItemRatings(input: String?): GameModels.GameListModelItemRatings? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListModelItemRatings::class.java)
        }
    }

    @TypeConverter
    fun gameListModelItemAddedByStatusToString(input: GameModels.GameListModelItemAddedByStatus?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListModelItemAddedByStatus(input: String?): GameModels.GameListModelItemAddedByStatus? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListModelItemAddedByStatus::class.java)
        }
    }

    @TypeConverter
    fun gameListModelItemDevelopersToString(input: GameModels.GameListModelItemDevelopers?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListModelItemDevelopers(input: String?): GameModels.GameListModelItemDevelopers? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListModelItemDevelopers::class.java)
        }
    }

    @TypeConverter
    fun gameListModelItemPublishersToString(input: GameModels.GameListModelItemPublishers?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToGameListModelItemPublishers(input: String?): GameModels.GameListModelItemPublishers? {
        return input?.let {
            gson.fromJson(it, GameModels.GameListModelItemPublishers::class.java)
        }
    }

    @TypeConverter
    fun jsonToString(input: JsonObject?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToJson(input: String?): JsonObject? {
        return input?.let {
            gson.fromJson(it, JsonObject::class.java)
        }
    }

    @TypeConverter
    fun listGameListResultModelToString(input: List<GameModels.GameListResultModel>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultModel(input: String?): List<GameModels.GameListResultModel>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultModel?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultModel>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListResultRatingsModelToString(input: List<GameModels.GameListResultRatingsModel>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultRatingsModel(input: String?): List<GameModels.GameListResultRatingsModel>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultRatingsModel?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultRatingsModel>>(it,type).toList()
        }
    }


    @TypeConverter
    fun listGameListResultPlatformsToString(input: List<GameModels.GameListResultPlatforms>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultPlatforms(input: String?): List<GameModels.GameListResultPlatforms>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultPlatforms?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultPlatforms>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListResultParentPlatformsToString(input: List<GameModels.GameListResultsParentPlatforms>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultParentPlatforms(input: String?): List<GameModels.GameListResultsParentPlatforms>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultsParentPlatforms?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultsParentPlatforms>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListResultGenresToString(input: List<GameModels.GameListResultGenres>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultGenres(input: String?): List<GameModels.GameListResultGenres>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultGenres?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultGenres>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListResultStoresToString(input: List<GameModels.GameListResultStores>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultStores(input: String?): List<GameModels.GameListResultStores>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultStores?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultStores>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListResultTagsToString(input: List<GameModels.GameListResultTags>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultTags(input: String?): List<GameModels.GameListResultTags>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultTags?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultTags>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListResultShortScreenshotsToString(input: List<GameModels.GameListResultShortScreenshots>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultScreenshots(input: String?): List<GameModels.GameListResultShortScreenshots>? {
        val type: Type = object : TypeToken<List<GameModels.GameListResultShortScreenshots?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListResultShortScreenshots>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListResultMetacriticPlatformsToString(input: List<GameModels.GameListModelItemMetacriticPlatforms>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListResultMetacriticPlatforms(input: String?): List<GameModels.GameListModelItemMetacriticPlatforms>? {
        val type: Type = object : TypeToken<List<GameModels.GameListModelItemMetacriticPlatforms?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListModelItemMetacriticPlatforms>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListModelItemRatingsToString(input: List<GameModels.GameListModelItemRatings>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListModelItemRatings(input: String?): List<GameModels.GameListModelItemRatings>? {
        val type: Type = object : TypeToken<List<GameModels.GameListModelItemRatings?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListModelItemRatings>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListModelItemDevelopersToString(input: List<GameModels.GameListModelItemDevelopers>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListModelItemDevelopers(input: String?): List<GameModels.GameListModelItemDevelopers>? {
        val type: Type = object : TypeToken<List<GameModels.GameListModelItemDevelopers?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListModelItemDevelopers>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListModelItemPublishersToString(input: List<GameModels.GameListModelItemPublishers>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListModelItemPublishers(input: String?): List<GameModels.GameListModelItemPublishers>? {
        val type: Type = object : TypeToken<List<GameModels.GameListModelItemPublishers?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<GameModels.GameListModelItemPublishers>>(it,type).toList()
        }
    }

    @TypeConverter
    fun listGameListModelItemAlternativeNamesToString(input: List<String>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToListGameListModelItemAlternativeNames(input: String?): List<String>? {
        val type: Type = object : TypeToken<List<String>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<String>>(it,type).toList()
        }
    }


}
