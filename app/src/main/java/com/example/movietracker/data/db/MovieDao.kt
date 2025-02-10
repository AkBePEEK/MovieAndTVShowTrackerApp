package com.example.movietracker.data.db

import androidx.room.*
import com.example.movietracker.model.Favorite
import com.example.movietracker.model.WatchlistItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlistItem(item: WatchlistItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)  // Add @Insert annotation here

    @Delete
    suspend fun deleteWatchlistItem(item: WatchlistItem)

    @Delete
    suspend fun removeFavorite(favorite: Favorite)  // Add @Delete annotation here

    @Query("SELECT * FROM watchlist_items")
    fun getWatchlist(): Flow<List<WatchlistItem>>

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<Favorite>>
}
