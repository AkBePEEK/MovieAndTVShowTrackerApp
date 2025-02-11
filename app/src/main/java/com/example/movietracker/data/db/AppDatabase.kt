package com.example.movietracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movietracker.model.Favorite
import com.example.movietracker.model.WatchlistItem

@Database(entities = [Favorite::class, WatchlistItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun movieDao(): MovieDao
}