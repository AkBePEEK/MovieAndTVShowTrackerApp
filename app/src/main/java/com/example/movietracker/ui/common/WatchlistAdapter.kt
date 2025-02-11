package com.example.movietracker.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietracker.databinding.ItemWatchlistBinding
import com.example.movietracker.model.Movie
import com.example.movietracker.model.WatchlistItem

class WatchlistAdapter(
    private val onRemoveClick: (Movie) -> Unit
) : ListAdapter<WatchlistItem, WatchlistAdapter.WatchlistViewHolder>(DIFF_CALLBACK) {

    private var watchlist: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val binding = ItemWatchlistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WatchlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        holder.bind(watchlist[position])
    }

    inner class WatchlistViewHolder(private val binding: ItemWatchlistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.textTitle.text = movie.title

            Glide.with(binding.root.context)
                .load(movie.posterPath)
                .into(binding.imagePoster)

            binding.buttonRemove.setOnClickListener {
                onRemoveClick(movie)
            }
        }
    }

    override fun getItemCount(): Int = watchlist.size

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WatchlistItem>() {
            override fun areItemsTheSame(oldItem: WatchlistItem, newItem: WatchlistItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WatchlistItem, newItem: WatchlistItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
