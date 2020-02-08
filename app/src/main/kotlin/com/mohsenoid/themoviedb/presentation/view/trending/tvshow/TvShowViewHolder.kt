package com.mohsenoid.themoviedb.presentation.view.trending.tvshow

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.model.TvShowModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tv_show_item.view.name
import kotlinx.android.synthetic.main.tv_show_item.view.poster

class TvShowViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.poster
    private val name: TextView = view.name

    fun setTvShow(tvShow: TvShowModel) {
        Picasso.get()
            .load(tvShow.posterUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(poster)

        name.text = tvShow.name
    }
}
