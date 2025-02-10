package com.example.movietracker.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietracker.model.Movie
import com.example.movietracker.ui.common.MovieAdapter
import com.example.movietracker.ui.details.DetailsFragment
import com.example.movietracker.viewmodel.FavoritesViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        val adapter = MovieAdapter { movie ->
            // Navigate to DetailsFragment
            val detailsFragment = DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt("movieId", movie.id)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favorites.collect { favorites ->
                val movies = favorites.map { favorite ->
                    Movie(
                        id = favorite.id,
                        title = favorite.title,
                        overview = "No Overview",  // Default or empty string for now
                        posterPath = favorite.posterPath,
                        backdropPath = null,       // Set to null as not available in Favorite
                        releaseDate = "TBD",       // Default value, you can adjust this based on your app's needs
                        voteAverage = 0.0,         // Default value for ratings
                        voteCount = 0,             // Default value for vote count
                        genreIds = emptyList()     // Default empty list for genres
                    )
                }
                adapter.submitList(movies)
            }
        }
    }
}
