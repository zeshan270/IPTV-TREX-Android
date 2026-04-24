package com.iptv_trex.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// Live TV Channels
@Entity(tableName = "channels")
data class ChannelEntity(
    @PrimaryKey
    val streamId: String,
    val name: String,
    val categoryId: String,
    val categoryName: String,
    val icon: String? = null,
    val lastWatched: Long = 0,
    val watchedDuration: Long = 0
)

// Movies (VOD)
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val streamId: String,
    val name: String,
    val categoryId: String,
    val categoryName: String,
    val cover: String? = null,
    val rating: String? = null,
    val duration: String? = null,
    val description: String? = null
)

// Series
@Entity(tableName = "series")
data class SeriesEntity(
    @PrimaryKey
    val seriesId: String,
    val name: String,
    val categoryId: String,
    val categoryName: String,
    val cover: String? = null,
    val rating: String? = null,
    val plot: String? = null
)

// Series Episodes
@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey
    val episodeId: String,
    val seriesId: String,
    val title: String,
    val season: String,
    val episode: String,
    val plot: String? = null,
    val rating: String? = null,
    val duration: String? = null,
    val airDate: String? = null
)

// Watchlist Items
@Entity(tableName = "watchlist")
data class WatchlistEntity(
    @PrimaryKey
    val itemId: String,
    val itemType: String, // "live", "movie", "series", "episode"
    val name: String,
    val icon: String? = null,
    val addedAt: Long = System.currentTimeMillis(),
    val position: Int = 0
)

// Favorites
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val itemId: String,
    val itemType: String, // "live", "movie", "series"
    val name: String,
    val icon: String? = null,
    val favoritedAt: Long = System.currentTimeMillis()
)

// User Preferences
@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey
    val id: Int = 0,
    val theme: String = "dark", // "light", "dark", "auto"
    val fontSize: Float = 1.0f,
    val autoplay: Boolean = true,
    val continuousPlayback: Boolean = true,
    val quality: String = "auto", // "auto", "720p", "1080p", "4K"
    val subtitles: Boolean = true,
    val subtitleLanguage: String = "en",
    val notificationsEnabled: Boolean = true,
    val analyticsEnabled: Boolean = false,
    val lastUpdated: Long = System.currentTimeMillis()
)
