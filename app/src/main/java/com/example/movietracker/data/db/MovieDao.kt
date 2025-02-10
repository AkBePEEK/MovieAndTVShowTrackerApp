package com.example.movietracker.data.db

import androidx.room.*
import com.example.movietracker.model.Favorite
import com.example.movietracker.model.WatchlistItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlistItem(item: WatchlistItem)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteWatchlistItem(item: WatchlistItem)
    suspend fun removeFavorite(favorite: Favorite)

    @Query("SELECT * FROM watchlist_items")
    fun getWatchlist(): Flow<List<WatchlistItem>>

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<Favorite>>
}
