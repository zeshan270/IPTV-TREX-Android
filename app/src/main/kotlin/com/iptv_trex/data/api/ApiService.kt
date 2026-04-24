package com.iptv_trex.data.api

import com.iptv_trex.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // User Authentication
    @GET("player_api.php")
    suspend fun getUserInfo(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_live_categories"
    ): Map<String, Any>

    // Live TV Categories
    @GET("player_api.php")
    suspend fun getLiveCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_live_categories"
    ): List<CategoryResponse>

    // Live Streams by Category
    @GET("player_api.php")
    suspend fun getLiveStreams(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_live_streams",
        @Query("category_id") categoryId: String
    ): List<LiveStream>

    // Movie Categories
    @GET("player_api.php")
    suspend fun getMovieCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_vod_categories"
    ): List<MovieCategory>

    // Movies by Category
    @GET("player_api.php")
    suspend fun getMovies(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_vod_streams",
        @Query("category_id") categoryId: String
    ): List<VodStream>

    // Movie Information
    @GET("player_api.php")
    suspend fun getMovieInfo(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_vod_info",
        @Query("vod_id") vodId: String
    ): Map<String, Any>

    // Series Categories
    @GET("player_api.php")
    suspend fun getSeriesCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_series_categories"
    ): List<SeriesCategory>

    // Series by Category
    @GET("player_api.php")
    suspend fun getSeries(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_series",
        @Query("category_id") categoryId: String
    ): List<Series>

    // Series Information & Episodes
    @GET("player_api.php")
    suspend fun getSeriesInfo(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_series_info",
        @Query("series_id") seriesId: String
    ): Map<String, Any>
}
