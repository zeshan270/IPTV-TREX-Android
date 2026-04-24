package com.iptv_trex.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// User Authentication
@Serializable
data class UserInfo(
    @SerialName("user_info")
    val userInfo: UserDetails? = null,
    @SerialName("server_info")
    val serverInfo: ServerInfo? = null
)

@Serializable
data class UserDetails(
    val username: String = "",
    val password: String = "",
    val auth: Int = 0,
    @SerialName("status")
    val status: String = "",
    @SerialName("exp_date")
    val expDate: String = "",
    @SerialName("is_trial")
    val isTrial: Int = 0,
    @SerialName("active_cons")
    val activeConnections: String = "",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("max_connections")
    val maxConnections: String = ""
)

@Serializable
data class ServerInfo(
    val url: String = "",
    val port: String = "",
    val https_port: String = "",
    @SerialName("server_protocol")
    val serverProtocol: String = ""
)

// Live TV Categories
@Serializable
data class CategoryResponse(
    @SerialName("category_id")
    val categoryId: String = "",
    @SerialName("category_name")
    val categoryName: String = ""
)

// Live TV Streams
@Serializable
data class LiveStream(
    @SerialName("name")
    val name: String = "",
    @SerialName("stream_id")
    val streamId: String = "",
    @SerialName("stream_icon")
    val streamIcon: String = "",
    @SerialName("category_id")
    val categoryId: String = "",
    @SerialName("category_name")
    val categoryName: String = ""
)

// VOD Categories
@Serializable
data class MovieCategory(
    @SerialName("category_id")
    val categoryId: String = "",
    @SerialName("category_name")
    val categoryName: String = ""
)

// VOD Streams (Movies)
@Serializable
data class VodStream(
    val name: String = "",
    @SerialName("stream_id")
    val streamId: String = "",
    @SerialName("stream_icon")
    val streamIcon: String = "",
    @SerialName("category_id")
    val categoryId: String = "",
    val rating: String = "",
    @SerialName("rating_5based")
    val ratingFiveBase: String = "",
    val duration: String = "",
    val description: String = ""
)

// Series Categories
@Serializable
data class SeriesCategory(
    @SerialName("category_id")
    val categoryId: String = "",
    @SerialName("category_name")
    val categoryName: String = ""
)

// Series
@Serializable
data class Series(
    val name: String = "",
    @SerialName("series_id")
    val seriesId: String = "",
    @SerialName("cover")
    val cover: String = "",
    @SerialName("category_id")
    val categoryId: String = "",
    val rating: String = "",
    val plot: String = ""
)

// Series Episodes
@Serializable
data class SeriesEpisode(
    @SerialName("id")
    val id: String = "",
    val title: String = "",
    @SerialName("season")
    val season: String = "",
    @SerialName("episode")
    val episode: String = "",
    @SerialName("plot")
    val plot: String = "",
    @SerialName("rating")
    val rating: String = "",
    @SerialName("duration")
    val duration: String = "",
    @SerialName("air_date")
    val airDate: String = ""
)

// Search Results
@Serializable
data class SearchResult(
    val type: String = "",
    val name: String = "",
    @SerialName("stream_id")
    val streamId: String = "",
    @SerialName("category_id")
    val categoryId: String = "",
    val icon: String? = null,
    val rating: String? = null
)

// Stream Information
@Serializable
data class StreamInfo(
    val name: String = "",
    val streamUrl: String = "",
    val iconUrl: String = "",
    val categoryId: String = "",
    val categoryName: String = "",
    val rating: String? = null,
    val description: String? = null,
    val duration: String? = null
)
