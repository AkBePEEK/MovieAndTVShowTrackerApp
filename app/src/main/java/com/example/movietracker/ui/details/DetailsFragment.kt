package com.example.movietracker.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.movietracker.viewmodel.MovieViewModel
import com.example.myapplication.databinding.FragmentDetailsBinding

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
        viewModel.movieDetails.observe(viewLifecycleOwner) { movie ->
            binding.titleTextView.text = movie.title
            binding.overviewTextView.text = movie.overview
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(binding.posterImageView)
            binding.favoriteButton.setOnClickListener {
                viewModel.addToFavorites(movieId, movie.title, movie.posterPath ?: "", "movie")
            }
        }
    }
}