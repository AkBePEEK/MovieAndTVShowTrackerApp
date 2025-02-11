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
import com.example.movietracker.R
import com.example.movietracker.databinding.FragmentFavoritesBinding
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favorites.collect { favorites ->
                val movies = favorites.map { favorite ->
                    Movie(
                        id = favorite.id,
                        title = favorite.title,
                        overview = favorite.overview,
                        posterPath = favorite.posterPath,
                        backdropPath = favorite.backdropPath,
                        releaseDate = favorite.releaseDate,
                        voteAverage = favorite.voteAverage,
                        voteCount = favorite.voteCount,
                        genreIds = favorite.genreIds ?: emptyList()
                    )
                }
                adapter.submitList(movies)
            }
        }
    }
}
