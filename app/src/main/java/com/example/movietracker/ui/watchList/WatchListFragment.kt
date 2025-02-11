package com.example.movietracker.ui.watchList

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
import com.example.movietracker.viewmodel.WatchlistViewModel
import com.example.movietracker.R
import com.example.movietracker.databinding.FragmentWatchlistBinding
import com.example.movietracker.ui.common.WatchlistAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {
    private lateinit var binding: FragmentWatchlistBinding
    private val viewModel: WatchlistViewModel by viewModels()
    @Inject
    lateinit var watchlistAdapter: WatchlistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        val adapter = MovieAdapter { movie ->
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

        // Observe watchlist from ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.watchlist.collect { watchlistItems ->
                val movies = watchlistItems.map { watchlistItem ->
                    Movie(
                        id = watchlistItem.id,
                        title = watchlistItem.title,
                        overview = "No Overview",  // Default or empty string for now
                        posterPath = watchlistItem.posterPath,
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