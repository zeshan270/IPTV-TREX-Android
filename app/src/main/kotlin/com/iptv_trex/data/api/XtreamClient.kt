package com.iptv_trex.data.api

import com.iptv_trex.data.model.*
import timber.log.Timber

class XtreamClient(
    private val serverUrl: String,
    private val username: String,
    private val password: String
) {
    private val apiService = RetrofitClient.getApiService(normalizeBaseUrl(serverUrl))

    companion object {
        private fun normalizeBaseUrl(url: String): String {
            var normalized = url.trim()
            if (!normalized.startsWith("http://") && !normalized.startsWith("https://")) {
                normalized = "http://$normalized"
            }
            if (!normalized.endsWith("/")) {
                normalized = "$normalized/"
            }
            return normalized
        }
    }

    // User Info
    suspend fun getUserInfo(): UserInfo {
        return try {
            val response = apiService.getUserInfo(username, password)
            Timber.d("User info: $response")
            UserInfo()
        } catch (e: Exception) {
            Timber.e(e, "Failed to get user info")
            throw e
        }
    }

    // Live Categories
    suspend fun getLiveCategories(): List<CategoryResponse> {
        return try {
            val categories = apiService.getLiveCategories(username, password)
            Timber.d("Got ${categories.size} live categories")
            categories
        } catch (e: Exception) {
            Timber.e(e, "Failed to get live categories")
            emptyList()
        }
    }

    // Live Streams
    suspend fun getLiveStreams(categoryId: String): List<LiveStream> {
        return try {
            val streams = apiService.getLiveStreams(
                username = username,
                password = password,
                categoryId = categoryId
            )
            Timber.d("Got ${streams.size} live streams in category $categoryId")
            streams
        } catch (e: Exception) {
            Timber.e(e, "Failed to get live streams")
            emptyList()
        }
    }

    // Movie Categories
    suspend fun getMovieCategories(): List<MovieCategory> {
        return try {
            val categories = apiService.getMovieCategories(username, password)
            Timber.d("Got ${categories.size} movie categories")
            categories
        } catch (e: Exception) {
            Timber.e(e, "Failed to get movie categories")
            emptyList()
        }
    }

    // Movies by Category
    suspend fun getMovies(categoryId: String): List<VodStream> {
        return try {
            val movies = apiService.getMovies(
                username = username,
                password = password,
                categoryId = categoryId
            )
            Timber.d("Got ${movies.size} movies in category $categoryId")
            movies
        } catch (e: Exception) {
            Timber.e(e, "Failed to get movies")
            emptyList()
        }
    }

    // Series Categories
    suspend fun getSeriesCategories(): List<SeriesCategory> {
        return try {
            val categories = apiService.getSeriesCategories(username, password)
            Timber.d("Got ${categories.size} series categories")
            categories
        } catch (e: Exception) {
            Timber.e(e, "Failed to get series categories")
            emptyList()
        }
    }

    // Series by Category
    suspend fun getSeries(categoryId: String): List<Series> {
        return try {
            val series = apiService.getSeries(
                username = username,
                password = password,
                categoryId = categoryId
            )
            Timber.d("Got ${series.size} series in category $categoryId")
            series
        } catch (e: Exception) {
            Timber.e(e, "Failed to get series")
            emptyList()
        }
    }

    // Generate Stream URLs
    fun getLiveStreamUrl(streamId: String): String {
        return "$serverUrl/live/$username/$password/$streamId.m3u8"
    }

    fun getMovieStreamUrl(streamId: String): String {
        return "$serverUrl/movie/$username/$password/$streamId.m3u8"
    }

    fun getSeriesEpisodeUrl(seriesId: String, seasonId: String, episodeId: String): String {
        return "$serverUrl/series/$username/$password/$seriesId/$seasonId/$episodeId.m3u8"
    }

    // Generate Image URLs
    fun getCoverImageUrl(imagePath: String?): String {
        return if (imagePath.isNullOrEmpty()) {
            "https://via.placeholder.com/300x450?text=No+Image"
        } else if (imagePath.startsWith("http")) {
            imagePath
        } else {
            "$serverUrl$imagePath"
        }
    }

    fun getIconUrl(iconPath: String?): String {
        return if (iconPath.isNullOrEmpty()) {
            "https://via.placeholder.com/100x100?text=No+Icon"
        } else if (iconPath.startsWith("http")) {
            iconPath
        } else {
            "$serverUrl$iconPath"
        }
    }
}
