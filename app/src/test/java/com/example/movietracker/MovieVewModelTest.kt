package com.example.movietracker

import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.Movie
import com.example.movietracker.viewmodel.MovieViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val repository: MovieRepository = mockk() // Mock repository
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun `fetchPopularMovies returns a list of movies`() = runTest {
        // Arrange
        val movies = listOf(
            Movie(
                id = 1,
                title = "Test Movie",
                overview = "Test Overview",
                posterPath = "/test.jpg",
                backdropPath = "/test_backdrop.jpg",
                releaseDate = "2025-01-01",
                voteAverage = 7.5,
                voteCount = 1000,
                genreIds = listOf(28, 12)
            )
        )
        coEvery { repository.getPopularMovies("api_key") } returns movies

        // Act
        viewModel.fetchPopularMovies("api_key")

        // Assert
        assertEquals(movies, viewModel.movies.value)
    }

    @Test
    fun `getMovieDetails returns correct movie details`() = runTest {
        // Arrange
        val movie = Movie(
            id = 1,
            title = "Test Movie",
            overview = "Test Overview",
            posterPath = "/test.jpg",
            backdropPath = "/test_backdrop.jpg",
            releaseDate = "2025-01-01",
            voteAverage = 7.5,
            voteCount = 1000,
            genreIds = listOf(28, 12)
        )
        coEvery { repository.getMovieDetails(1, "api_key") } returns movie

        // Act
        viewModel.getMovieDetails(1, "api_key")

        // Assert
        assertEquals(movie, viewModel.movieDetails.value)
    }
}
