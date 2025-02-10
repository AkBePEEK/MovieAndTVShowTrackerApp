package com.example.movietracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.WatchlistItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val watchlist: Flow<List<WatchlistItem>> = repository.getWatchlist()

    fun removeFromWatchlist(item: WatchlistItem) {
        viewModelScope.launch {
            repository.removeWatchlistItem(item)
        }
    }
}