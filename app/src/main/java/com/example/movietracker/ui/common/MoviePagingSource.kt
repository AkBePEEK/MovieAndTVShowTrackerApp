package com.example.movietracker.ui.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.Movie

class MoviePagingSource(private val repository: MovieRepository) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getPopularMoviesPage(nextPage)  // Modify repo to handle paginated API
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
