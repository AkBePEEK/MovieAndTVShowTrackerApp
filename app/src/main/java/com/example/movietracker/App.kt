package com.example.movietracker

import android.app.Application
import androidx.room.Room
import com.example.movietracker.data.db.AppDatabase

class App : Application() {
    override fun onCreate() = super.onCreate()
    val database by lazy { Room.databaseBuilder(this, AppDatabase::class.java, "movie_db").build() }
}