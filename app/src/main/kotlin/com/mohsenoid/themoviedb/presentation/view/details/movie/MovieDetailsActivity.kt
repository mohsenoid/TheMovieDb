package com.mohsenoid.themoviedb.presentation.view.details.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.base.BaseActivity
import com.mohsenoid.themoviedb.presentation.model.MovieModel
import com.mohsenoid.themoviedb.presentation.model.TrailerModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details.overview
import kotlinx.android.synthetic.main.movie_details.progress
import kotlinx.android.synthetic.main.movie_details.rating
import kotlinx.android.synthetic.main.movie_details.ratingBar
import kotlinx.android.synthetic.main.movie_details.releaseDate
import kotlinx.android.synthetic.main.movie_details.trailer
import kotlinx.android.synthetic.main.movie_details_activity.poster
import kotlinx.android.synthetic.main.movie_details_activity.toolbar
import kotlinx.coroutines.launch
import org.koin.android.scope.lifecycleScope
import timber.log.Timber

class MovieDetailsActivity : BaseActivity(),
    MovieDetailsContract.View {

    private val presenter: MovieDetailsContract.Presenter by lifecycleScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details_activity)

        val movie: MovieModel? = intent.extras?.getParcelable(ARG_MODEL)

        if (movie == null) {
            Timber.w("model is null!")
            onBackPressed()
            return
        }

        presenter.bind(this)

        initToolbar(movie)
        initView(movie)
        loadTrailer(movie)
    }

    private fun initToolbar(movie: MovieModel) {
        toolbar.title = movie.title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView(movie: MovieModel) {
        Picasso.get()
            .load(movie.posterUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(poster)

        ratingBar.rating = (movie.voteAverage / 2).toFloat()
        rating.text = String.format(getString(R.string.rating), movie.voteAverage)
        overview.text = movie.overview
        releaseDate.text = movie.releaseDate
    }

    private fun loadTrailer(movieModel: MovieModel) {
        launch {
            presenter.loadTrailer(movieModel.id)
        }
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

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showOfflineMessage(isCritical: Boolean) {
        Toast.makeText(this, R.string.offline_app, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun setTrailerResult(trailerModel: TrailerModel) {
        trailer.visibility = View.VISIBLE
        trailer.text = trailerModel.name
        trailer.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW)
            webIntent.data = Uri.parse(trailerModel.url)
            webIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(webIntent)
        }
    }

    companion object {
        private const val ARG_MODEL = "model"

        fun newIntent(context: Context?, movieModel: MovieModel): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(ARG_MODEL, movieModel)
            return intent
        }
    }
}
