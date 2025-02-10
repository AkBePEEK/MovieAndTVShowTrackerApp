package com.example.movietracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietracker.ui.common.MovieAdapter
import com.example.movietracker.viewmodel.MovieViewModel
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        val adapter = MovieAdapter { /* No action for now */ }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Observe movies from ViewModel
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }

        // Fetch trending movies
        viewModel.fetchPopularMovies("YOUR_API_KEY")
    }
}