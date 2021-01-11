package com.example.videogamesapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.videogamesapplication.room.RoomConverters
import com.google.gson.JsonObject

class GameModels() {
    @Entity(tableName = "GameList")
    @TypeConverters(RoomConverters::class)
    data class GameListModel(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "count") val count: Int? = null,
        @ColumnInfo(name = "next") val next: String? = null,
        @ColumnInfo(name = "previous") val previous: String? = null,
        @ColumnInfo(name = "results") val results: List<GameListResultModel?> = listOf()
    )

    @Entity(tableName = "GameListResult")
    @TypeConverters(RoomConverters::class)
    data class GameListResultModel(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "released") val released: String? = null,
        @ColumnInfo(name = "tba") val tba: Boolean? = null,
        @ColumnInfo(name = "background_image") val background_image: String? = null,
        @ColumnInfo(name = "rating") val rating: Double? = null,
        @ColumnInfo(name = "rating_top") val rating_top: Double? = null,
        @ColumnInfo(name = "ratings") val ratings: List<GameListResultRatingsModel?> = listOf(),
        @ColumnInfo(name = "ratings_count") val ratings_count: Int? = null,
        @ColumnInfo(name = "reviews_text_count") val reviews_text_count: Int? = null,
        @ColumnInfo(name = "added") val added: Int? = null,
        @ColumnInfo(name = "added_by_status") val added_by_status: GameListResultAddedByStatus? = null,
        @ColumnInfo(name = "metacritic") val metacritic: Int? = null,
        @ColumnInfo(name = "playtime") val playtime: Int? = null,
        @ColumnInfo(name = "suggestion_count") val suggestion_count: Int? = null,
        @ColumnInfo(name = "updated") val updated: String? = null,
        @ColumnInfo(name = "user_game") val user_game: String? = null,
        @ColumnInfo(name = "reviews_count") val reviews_count: Int? = null,
        @ColumnInfo(name = "saturated_color") val saturated_color: String? = null,
        @ColumnInfo(name = "dominant_color") val dominant_color: String? = null,
        @ColumnInfo(name = "platforms") val platforms: List<GameListResultPlatforms?> = listOf(),
        @ColumnInfo(name = "parent_platforms") val parent_platforms: List<GameListResultsParentPlatforms?> = listOf(),
        @ColumnInfo(name = "genres") val genres: List<GameListResultGenres?> = listOf(),
        @ColumnInfo(name = "stores") val stores: List<GameListResultStores?> = listOf(),
        @ColumnInfo(name = "clip") val clip: GameListResultClip? = null,
        @ColumnInfo(name = "tags") val tags: List<GameListResultTags?> = listOf(),
        @ColumnInfo(name = "esrb_rating") val esrb_rating: GameListResultEsrbRating? = null,
        @ColumnInfo(name = "short_screenshots") val short_screenshots: List<GameListResultShortScreenshots?> = listOf()
    )

    @Entity(tableName = "GameListResultRatings")
    @TypeConverters(RoomConverters::class)
    data class GameListResultRatingsModel(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "title") val title: String? = null,
        @ColumnInfo(name = "count") val count: Int? = null,
        @ColumnInfo(name = "percent") val percent: Double? = null
    )


    @Entity(tableName = "GameListResultAddedByStatus")
    @TypeConverters(RoomConverters::class)
    data class GameListResultAddedByStatus(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "yet") val yet: Int? = null,
        @ColumnInfo(name = "owned") val owned: Int? = null,
        @ColumnInfo(name = "beaten") val beaten: Int? = null,
        @ColumnInfo(name = "toplay") val toplay: Int? = null,
        @ColumnInfo(name = "dropped") val dropped: Int? = null,
        @ColumnInfo(name = "playing") val playing: Int? = null
    )

    @Entity(tableName = "GameListResultPlatforms")
    @TypeConverters(RoomConverters::class)
    data class GameListResultPlatforms(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "platform") val platform: GameListResultPlatformsPlatform? = null,
        @ColumnInfo(name = "released_at") val released_at: String? = null,
        @ColumnInfo(name = "requirements_en") val requirements_en: GameListResultsPlatformsRequirementsEn? = null,
        @ColumnInfo(name = "requirementsRu") val requirementsRu: GameListResultsPlatformsRequirementsRu? = null
    )

    @Entity(tableName = "GameListResultPlatformsPlatform")
    @TypeConverters(RoomConverters::class)
    data class GameListResultPlatformsPlatform(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "image") val image: String? = null,
        @ColumnInfo(name = "year_end") val year_end: String? = null,
        @ColumnInfo(name = "year_start") val year_start: String? = null,
        @ColumnInfo(name = "games_count") val games_count: Int? = null,
        @ColumnInfo(name = "image_background") val image_background: String? = null
    )

    @Entity(tableName = "GameListResultPlatformsRequirementsEn")
    @TypeConverters(RoomConverters::class)
    data class GameListResultsPlatformsRequirementsEn(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "minimum") val minimum: String? = null,
        @ColumnInfo(name = "recommended") val recommended: String? = null
    )

    @Entity(tableName = "GameListResultPlatformsRequirementsRu")
    @TypeConverters(RoomConverters::class)
    data class GameListResultsPlatformsRequirementsRu(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "minimum") val minimum: String? = null,
        @ColumnInfo(name = "recommended") val recommended: String? = null
    )

    @Entity(tableName = "GameListResultsParentPlatforms")
    @TypeConverters(RoomConverters::class)
    data class GameListResultsParentPlatforms(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "platform") val platform: GameListResultParentPlatformsPlatform? = null
    )

    @Entity(tableName = "GameListResultParentPlatformsPlatform")
    @TypeConverters(RoomConverters::class)
    data class GameListResultParentPlatformsPlatform(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null
    )

    @Entity(tableName = "GameListResultGenres")
    @TypeConverters(RoomConverters::class)
    data class GameListResultGenres(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "games_count") val games_count: Int? = null,
        @ColumnInfo(name = "image_background") val image_background: String? = null
    )

    @Entity(tableName = "GameListResultStores")
    @TypeConverters(RoomConverters::class)
    data class GameListResultStores(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "store") val store: GameListResultStoresStore? = null
    )

    @Entity(tableName = "GameListResultStoresStore")
    @TypeConverters(RoomConverters::class)
    data class GameListResultStoresStore(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "domain") val domain: String? = null,
        @ColumnInfo(name = "games_count") val games_count: Int? = null,
        @ColumnInfo(name = "image_background") val image_background: String? = null
    )

    @Entity(tableName = "GameListResultClip")
    @TypeConverters(RoomConverters::class)
    data class GameListResultClip(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "clip") val clip: String? = null,
        @ColumnInfo(name = "clips") val clips: JsonObject? = null,
        @ColumnInfo(name = "video") val video: String? = null,
        @ColumnInfo(name = "preview") val preview: String? = null
    )

    @Entity(tableName = "GameListResultTags")
    @TypeConverters(RoomConverters::class)
    data class GameListResultTags(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "domain") val domain: String? = null,
        @ColumnInfo(name = "games_count") val games_count: Int? = null,
        @ColumnInfo(name = "image_background") val image_background: String? = null
    )

    @Entity(tableName = "GameListResultEsrbRating")
    @TypeConverters(RoomConverters::class)
    data class GameListResultEsrbRating(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null
    )

    @Entity(tableName = "GameListResultShortScreenshots")
    @TypeConverters(RoomConverters::class)
    data class GameListResultShortScreenshots(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "image") val image: String? = null
    )

    @Entity(tableName = "GameListModelItem")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItem(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "name_original") val name_original: String? = null,
        @ColumnInfo(name = "description") val description: String? = null,
        @ColumnInfo(name = "metacritic") val metacritic: Int? = null,
        @ColumnInfo(name = "metacritic_platforms") val metacritic_platforms: List<GameListModelItemMetacriticPlatforms>? = listOf(),
        @ColumnInfo(name = "released") val released: String? = null,
        @ColumnInfo(name = "tba") val tba: Boolean? = null,
        @ColumnInfo(name = "updated") val updated: String? = null,
        @ColumnInfo(name = "background_image") val background_image: String? = null,
        @ColumnInfo(name = "background_image_additional") val background_image_additional: String? = null,
        @ColumnInfo(name = "website") val website: String? = null,
        @ColumnInfo(name = "rating") val rating: String? = null,
        @ColumnInfo(name = "rating_top") val rating_top: String? = null,
        @ColumnInfo(name = "ratings") val ratings: List<GameListModelItemRatings?> = listOf(),
        @ColumnInfo(name = "reactions") val reactions: JsonObject? = null,
        @ColumnInfo(name = "added") val added: Int? = null,
        @ColumnInfo(name = "added_by_status") val added_by_status: GameListModelItemAddedByStatus? = null,
        @ColumnInfo(name = "playtime") val playtime: Int? = null,
        @ColumnInfo(name = "screenshots_count") val screenshots_count: Int? = null,
        @ColumnInfo(name = "movies_count") val movies_count: Int? = null,
        @ColumnInfo(name = "creators_count") val creators_count: Int? = null,
        @ColumnInfo(name = "achievements_count") val achievements_count: Int? = null,
        @ColumnInfo(name = "parent_achievements_count") val parent_achievements_count: Int? = null,
        @ColumnInfo(name = "reddit_url") val reddit_url: String? = null,
        @ColumnInfo(name = "reddit_name") val reddit_name: String? = null,
        @ColumnInfo(name = "reddit_description") val reddit_description: String? = null,
        @ColumnInfo(name = "reddit_logo") val reddit_logo: String? = null,
        @ColumnInfo(name = "reddit_count") val reddit_count: Int? = null,
        @ColumnInfo(name = "twitch_count") val twitch_count: Int? = null,
        @ColumnInfo(name = "youtube_count") val youtube_count: Int? = null,
        @ColumnInfo(name = "reviews_text_count") val reviews_text_count: Int? = null,
        @ColumnInfo(name = "ratings_count") val ratings_count: Int? = null,
        @ColumnInfo(name = "suggestion_count") val suggestion_count: Int? = null,
        @ColumnInfo(name = "alternative_names") val alternative_names: List<String?> = listOf(),
        @ColumnInfo(name = "metacritic_url") val metacritic_url: String? = null,
        @ColumnInfo(name = "parents_count") val parents_count: Int? = null,
        @ColumnInfo(name = "additions_count") val additions_count: Int? = null,
        @ColumnInfo(name = "game_series_count") val game_series_count: Int? = null,
        @ColumnInfo(name = "user_game") val user_game: String? = null,
        @ColumnInfo(name = "reviews_count") val reviews_count: Int? = null,
        @ColumnInfo(name = "saturated_color") val saturated_color: String? = null,
        @ColumnInfo(name = "dominant_color") val dominant_color: String? = null,
        @ColumnInfo(name = "parent_platforms") val parent_platforms: List<GameListResultsParentPlatforms?> = listOf(),
        @ColumnInfo(name = "platforms") val platforms: List<GameListResultPlatforms?> = listOf(),
        @ColumnInfo(name = "stores") val stores: List<GameListResultStores?> = listOf(),
        @ColumnInfo(name = "developers") val developers: List<GameListModelItemDevelopers?> = listOf(),
        @ColumnInfo(name = "genres") val genres: List<GameListResultGenres?> = listOf(),
        @ColumnInfo(name = "tags") val tags: List<GameListResultTags?> = listOf(),
        @ColumnInfo(name = "publishers") val publishers: List<GameListModelItemPublishers?> = listOf(),
        @ColumnInfo(name = "esrb_rating") val esrb_rating: GameListResultEsrbRating? = null,
        @ColumnInfo(name = "clip") val clip: GameListResultClip? = null,
        @ColumnInfo(name = "description_raw") val description_raw: String? = null
    )

    @Entity(tableName = "GameListModelItemDetails")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItemDetails(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "name_original") val name_original: String? = null,
        @ColumnInfo(name = "description") val description: String? = null,
        @ColumnInfo(name = "metacritic") val metacritic: Int? = null,
        @ColumnInfo(name = "metacritic_platforms") val metacritic_platforms: List<GameListModelItemMetacriticPlatforms>? = listOf(),
        @ColumnInfo(name = "released") val released: String? = null,
        @ColumnInfo(name = "tba") val tba: Boolean? = null,
        @ColumnInfo(name = "updated") val updated: String? = null,
        @ColumnInfo(name = "background_image") val background_image: String? = null,
        @ColumnInfo(name = "background_image_additional") val background_image_additional: String? = null,
        @ColumnInfo(name = "website") val website: String? = null,
        @ColumnInfo(name = "rating") val rating: String? = null,
        @ColumnInfo(name = "rating_top") val rating_top: String? = null,
        @ColumnInfo(name = "ratings") val ratings: List<GameListModelItemRatings?> = listOf(),
        @ColumnInfo(name = "reactions") val reactions: JsonObject? = null,
        @ColumnInfo(name = "added") val added: Int? = null,
        @ColumnInfo(name = "added_by_status") val added_by_status: GameListModelItemAddedByStatus? = null,
        @ColumnInfo(name = "playtime") val playtime: Int? = null,
        @ColumnInfo(name = "screenshots_count") val screenshots_count: Int? = null,
        @ColumnInfo(name = "movies_count") val movies_count: Int? = null,
        @ColumnInfo(name = "creators_count") val creators_count: Int? = null,
        @ColumnInfo(name = "achievements_count") val achievements_count: Int? = null,
        @ColumnInfo(name = "parent_achievements_count") val parent_achievements_count: Int? = null,
        @ColumnInfo(name = "reddit_url") val reddit_url: String? = null,
        @ColumnInfo(name = "reddit_name") val reddit_name: String? = null,
        @ColumnInfo(name = "reddit_description") val reddit_description: String? = null,
        @ColumnInfo(name = "reddit_logo") val reddit_logo: String? = null,
        @ColumnInfo(name = "reddit_count") val reddit_count: Int? = null,
        @ColumnInfo(name = "twitch_count") val twitch_count: Int? = null,
        @ColumnInfo(name = "youtube_count") val youtube_count: Int? = null,
        @ColumnInfo(name = "reviews_text_count") val reviews_text_count: Int? = null,
        @ColumnInfo(name = "ratings_count") val ratings_count: Int? = null,
        @ColumnInfo(name = "suggestion_count") val suggestion_count: Int? = null,
        @ColumnInfo(name = "alternative_names") val alternative_names: List<String?> = listOf(),
        @ColumnInfo(name = "metacritic_url") val metacritic_url: String? = null,
        @ColumnInfo(name = "parents_count") val parents_count: Int? = null,
        @ColumnInfo(name = "additions_count") val additions_count: Int? = null,
        @ColumnInfo(name = "game_series_count") val game_series_count: Int? = null,
        @ColumnInfo(name = "user_game") val user_game: String? = null,
        @ColumnInfo(name = "reviews_count") val reviews_count: Int? = null,
        @ColumnInfo(name = "saturated_color") val saturated_color: String? = null,
        @ColumnInfo(name = "dominant_color") val dominant_color: String? = null,
        @ColumnInfo(name = "parent_platforms") val parent_platforms: List<GameListResultsParentPlatforms?> = listOf(),
        @ColumnInfo(name = "platforms") val platforms: List<GameListResultPlatforms?> = listOf(),
        @ColumnInfo(name = "stores") val stores: List<GameListResultStores?> = listOf(),
        @ColumnInfo(name = "developers") val developers: List<GameListModelItemDevelopers?> = listOf(),
        @ColumnInfo(name = "genres") val genres: List<GameListResultGenres?> = listOf(),
        @ColumnInfo(name = "tags") val tags: List<GameListResultTags?> = listOf(),
        @ColumnInfo(name = "publishers") val publishers: List<GameListModelItemPublishers?> = listOf(),
        @ColumnInfo(name = "esrb_rating") val esrb_rating: GameListResultEsrbRating? = null,
        @ColumnInfo(name = "clip") val clip: GameListResultClip? = null,
        @ColumnInfo(name = "description_raw") val description_raw: String? = null
    )

    @Entity(tableName = "GameListModelItemMetacriticPlatforms")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItemMetacriticPlatforms(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "metascore") val metascore: Int? = null,
        @ColumnInfo(name = "url") val url: String? = null,
        @ColumnInfo(name = "platform") val platform: GameListModelItemMetacriticPlatformsPlatform? = null
    )

    @Entity(tableName = "GameListModelItemMetacriticPlatformsPlatform")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItemMetacriticPlatformsPlatform(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "platform") val platform: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null
    )

    @Entity(tableName = "GameListModelItemRatings")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItemRatings(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "title") val title: String? = null,
        @ColumnInfo(name = "count") val count: Int? = null,
        @ColumnInfo(name = "percent") val percent: Double? = null
    )

    @Entity(tableName = "GameListModelItemAddedByStatus")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItemAddedByStatus(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "yet") val yet: Int? = null,
        @ColumnInfo(name = "owned") val owned: Int? = null,
        @ColumnInfo(name = "beaten") val beaten: Int? = null,
        @ColumnInfo(name = "toplay") val toplay: Int? = null,
        @ColumnInfo(name = "dropped") val dropped: Int? = null,
        @ColumnInfo(name = "playing") val playing: Int? = null
    )

    @Entity(tableName = "GameListModelItemDevelopers")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItemDevelopers(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "games_count") val games_count: Int? = null,
        @ColumnInfo(name = "image_background") val image_background: String? = null
    )

    @Entity(tableName = "GameListModelItemPublishers")
    @TypeConverters(RoomConverters::class)
    data class GameListModelItemPublishers(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "id") val id: Int? = null,
        @ColumnInfo(name = "name") val name: String? = null,
        @ColumnInfo(name = "slug") val slug: String? = null,
        @ColumnInfo(name = "games_count") val games_count: Int? = null,
        @ColumnInfo(name = "image_background") val image_background: String? = null
    )

}