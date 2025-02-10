package com.example.movietracker.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movietracker.viewmodel.MovieViewModel
import com.example.myapplication.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get movie ID from arguments
        val movieId = arguments?.getInt("movieId") ?: return

        // Fetch movie details
        viewModel.getMovieDetails(movieId, "YOUR_API_KEY")

        // Observe movie details
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieDetails.collect { movie ->
                if (movie != null) {
                    binding.titleTextView.text = movie.title
                }
                if (movie != null) {
                    binding.overviewTextView.text = movie.overview.takeIf { it.isNotEmpty() } ?: "No overview available."
                }
                if (movie != null) {
                    binding.voteAverageTextView.text = if (movie.voteAverage > 0) movie.voteAverage.toString() else "No rating yet"
                }
                if (movie != null) {
                    Glide.with(requireContext())
                        .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                        .into(binding.posterImageView)
                }
                binding.favoriteButton.setOnClickListener {
                    if (movie != null) {
                        viewModel.addToFavorites(movieId, movie.title, movie.posterPath ?: "", "movie")
                    }
                }
            }
        }
    }
}