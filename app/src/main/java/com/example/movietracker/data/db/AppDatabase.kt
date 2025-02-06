package com.example.movietracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movietracker.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}