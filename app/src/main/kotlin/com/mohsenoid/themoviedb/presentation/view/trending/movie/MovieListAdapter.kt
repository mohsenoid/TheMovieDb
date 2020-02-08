package com.mohsenoid.themoviedb.presentation.view.trending.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.model.MovieModel

class MovieListAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<MovieModel> = emptyList()

    fun setModels(movies: List<MovieModel>) {
        this.movies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieEntity = movies[position]

        holder.setMovieEntity(movieEntity)
        holder.view.setOnClickListener { listener.onMovieClick(movieEntity) }
    }

    override fun getItemCount(): Int = movies.size

    interface ClickListener {
        fun onMovieClick(movie: MovieModel)
    }
}
