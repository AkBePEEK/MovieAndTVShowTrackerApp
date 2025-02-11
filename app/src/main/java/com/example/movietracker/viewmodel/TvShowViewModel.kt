package com.example.movietracker.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val popularTvShows: Flow<List<TvShow>> = flow {
        val shows = repository.getPopularTvShows("0b4a614651d00362dbf058e488e6fa90")
        emit(shows)
    }.flowOn(Dispatchers.IO)
}
