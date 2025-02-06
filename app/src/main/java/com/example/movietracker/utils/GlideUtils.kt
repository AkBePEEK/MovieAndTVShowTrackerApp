package com.example.movietracker.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideUtils {
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500$url")
            .into(imageView)
    }
}