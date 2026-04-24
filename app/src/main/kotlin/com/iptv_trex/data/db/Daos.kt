package com.iptv_trex.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// Channels DAO
@Dao
interface ChannelDao {
    @Query("SELECT * FROM channels ORDER BY name ASC")
    fun getAllChannels(): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getChannelsByCategory(categoryId: String): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE streamId = :streamId")
    suspend fun getChannelById(streamId: String): ChannelEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannel(channel: ChannelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannels(channels: List<ChannelEntity>)

    @Update
    suspend fun updateChannel(channel: ChannelEntity)

    @Delete
    suspend fun deleteChannel(channel: ChannelEntity)

    @Query("DELETE FROM channels")
    suspend fun deleteAllChannels()
}

// Movies DAO
@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY name ASC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getMoviesByCategory(categoryId: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE streamId = :streamId")
    suspend fun getMovieById(streamId: String): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()
}

// Series DAO
@Dao
interface SeriesDao {
    @Query("SELECT * FROM series ORDER BY name ASC")
    fun getAllSeries(): Flow<List<SeriesEntity>>

    @Query("SELECT * FROM series WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getSeriesByCategory(categoryId: String): Flow<List<SeriesEntity>>

    @Query("SELECT * FROM series WHERE seriesId = :seriesId")
    suspend fun getSeriesById(seriesId: String): SeriesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(series: SeriesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeriesCollection(seriesList: List<SeriesEntity>)

    @Update
    suspend fun updateSeries(series: SeriesEntity)

    @Delete
    suspend fun deleteSeries(series: SeriesEntity)

    @Query("DELETE FROM series")
    suspend fun deleteAllSeries()
}

// Episodes DAO
@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episodes WHERE seriesId = :seriesId ORDER BY season ASC, episode ASC")
    fun getEpisodesBySeriesId(seriesId: String): Flow<List<EpisodeEntity>>

    @Query("SELECT * FROM episodes WHERE episodeId = :episodeId")
    suspend fun getEpisodeById(episodeId: String): EpisodeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: EpisodeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Update
    suspend fun updateEpisode(episode: EpisodeEntity)

    @Delete
    suspend fun deleteEpisode(episode: EpisodeEntity)

    @Query("DELETE FROM episodes WHERE seriesId = :seriesId")
    suspend fun deleteEpisodesBySeries(seriesId: String)

    @Query("DELETE FROM episodes")
    suspend fun deleteAllEpisodes()
}

// Watchlist DAO
@Dao
interface WatchlistDao {
    @Query("SELECT * FROM watchlist ORDER BY position ASC")
    fun getAllWatchlistItems(): Flow<List<WatchlistEntity>>

    @Query("SELECT * FROM watchlist WHERE itemId = :itemId")
    suspend fun getWatchlistItem(itemId: String): WatchlistEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlistItem(item: WatchlistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlistItems(items: List<WatchlistEntity>)

    @Update
    suspend fun updateWatchlistItem(item: WatchlistEntity)

    @Delete
    suspend fun deleteWatchlistItem(item: WatchlistEntity)

    @Query("DELETE FROM watchlist WHERE itemId = :itemId")
    suspend fun deleteWatchlistItemById(itemId: String)

    @Query("DELETE FROM watchlist")
    suspend fun deleteAllWatchlistItems()
}

// Favorites DAO
@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY favoritedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE itemId = :itemId")
    suspend fun getFavorite(itemId: String): FavoriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(favorites: List<FavoriteEntity>)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE itemId = :itemId")
    suspend fun deleteFavoriteById(itemId: String)

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavorites()
}

// User Preferences DAO
@Dao
interface UserPreferencesDao {
    @Query("SELECT * FROM user_preferences WHERE id = 0")
    fun getPreferences(): Flow<UserPreferencesEntity?>

    @Query("SELECT * FROM user_preferences WHERE id = 0")
    suspend fun getPreferencesSync(): UserPreferencesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferences(preferences: UserPreferencesEntity)

    @Update
    suspend fun updatePreferences(preferences: UserPreferencesEntity)

    @Query("DELETE FROM user_preferences")
    suspend fun deleteAllPreferences()
}
