package com.mohsenoid.themoviedb.presentation.view.trending.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.model.TvShowModel

class TvShowListAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<TvShowViewHolder>() {

    private var tvShows: List<TvShowModel> = emptyList()

    fun setTvShows(tvShows: List<TvShowModel>) {
        this.tvShows = tvShows
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tv_show_item, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow: TvShowModel = tvShows[position]

        holder.setTvShow(tvShow)
        holder.view.setOnClickListener { listener.onTvShowClick(tvShow) }
    }

    override fun getItemCount(): Int = tvShows.size

    interface ClickListener {
        fun onTvShowClick(tvShow: TvShowModel)
    }
}
