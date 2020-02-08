package com.mohsenoid.themoviedb.presentation.view.trending.movie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.model.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.poster
import kotlinx.android.synthetic.main.movie_item.view.title

class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.poster
    private val title: TextView = view.title

    fun setMovieEntity(movieModel: MovieModel) {
        Picasso.get()
            .load(movieModel.posterUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(poster)

        title.text = movieModel.title
    }
}
