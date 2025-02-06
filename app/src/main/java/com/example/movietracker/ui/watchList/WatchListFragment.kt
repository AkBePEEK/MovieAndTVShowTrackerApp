package com.example.movietracker.ui.watchList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietracker.ui.common.MovieAdapter
import com.example.movietracker.ui.details.DetailsFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWatchlistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchlistFragment : Fragment() {
    private lateinit var binding: FragmentWatchlistBinding
    private val viewModel: WatchlistViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false)
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

        // Observe watchlist from ViewModel
        viewModel.watchlist.observe(viewLifecycleOwner) { watchlist ->
            adapter.submitList(watchlist)
        }
    }
}