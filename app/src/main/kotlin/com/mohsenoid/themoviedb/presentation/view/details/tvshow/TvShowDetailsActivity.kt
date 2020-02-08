package com.mohsenoid.themoviedb.presentation.view.details.tvshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.base.BaseActivity
import com.mohsenoid.themoviedb.presentation.model.TvShowModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tv_show_details.firstAirDate
import kotlinx.android.synthetic.main.tv_show_details.overview
import kotlinx.android.synthetic.main.tv_show_details.rating
import kotlinx.android.synthetic.main.tv_show_details.ratingBar
import kotlinx.android.synthetic.main.tv_show_details_activity.poster
import kotlinx.android.synthetic.main.tv_show_details_activity.toolbar
import timber.log.Timber

class TvShowDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_show_details_activity)

        val tvShow: TvShowModel? = intent.extras?.getParcelable(ARG_TV_SHOW_MODEL)

        if (tvShow == null) {
            Timber.w("model is null!")
            onBackPressed()
            return
        }

        initToolbar(tvShow)
        initView(tvShow)
    }

    private fun initToolbar(tvShow: TvShowModel) {
        toolbar.title = tvShow.name
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView(tvShow: TvShowModel) {
        Picasso.get()
            .load(tvShow.posterUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(poster)

        ratingBar.rating = (tvShow.voteAverage / 2).toFloat()
        rating.text = String.format(getString(R.string.rating), tvShow.voteAverage)
        overview.text = tvShow.overview
        firstAirDate.text = tvShow.firstAirDate
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> false
        }
    }

    companion object {
        private const val ARG_TV_SHOW_MODEL = "tvShowModel"

        fun newIntent(context: Context?, tvShow: TvShowModel): Intent {
            val intent = Intent(context, TvShowDetailsActivity::class.java)
            intent.putExtra(ARG_TV_SHOW_MODEL, tvShow)
            return intent
        }
    }
}
