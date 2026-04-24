package com.iptv_trex.ui.navigation

sealed class Route(val route: String) {
    object Login : Route("login")
    object Home : Route("home")
    object Live : Route("live")
    object LiveCategory : Route("live/{categoryId}") {
        fun createRoute(categoryId: String) = "live/$categoryId"
    }
    object Player : Route("player/{streamType}/{streamId}") {
        fun createRoute(streamType: String, streamId: String) = "player/$streamType/$streamId"
    }
    object Movies : Route("movies")
    object MovieCategory : Route("movies/{categoryId}") {
        fun createRoute(categoryId: String) = "movies/$categoryId"
    }
    object MovieDetail : Route("movie/{movieId}") {
        fun createRoute(movieId: String) = "movie/$movieId"
    }
    object Series : Route("series")
    object SeriesCategory : Route("series/{categoryId}") {
        fun createRoute(categoryId: String) = "series/$categoryId"
    }
    object SeriesDetail : Route("series/{seriesId}") {
        fun createRoute(seriesId: String) = "series/$seriesId"
    }
    object Watchlist : Route("watchlist")
    object Favorites : Route("favorites")
    object Settings : Route("settings")
    object Profile : Route("profile")
}
