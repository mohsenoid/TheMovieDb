package com.mohsenoid.themoviedb.presentation.view.trending

import android.os.Bundle
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.base.BaseActivity
import com.mohsenoid.themoviedb.presentation.view.trending.movie.TrendingMovieListFragment
import com.mohsenoid.themoviedb.presentation.view.trending.tvshow.TrendingTvShowListFragment

class TrendingActivity : BaseActivity() {

    private lateinit var trendingMovieListFragment: TrendingMovieListFragment
    private lateinit var trendingTvShowListFragment: TrendingTvShowListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trending_activity)

        if (savedInstanceState == null) {
            trendingMovieListFragment = TrendingMovieListFragment.newInstance()
            trendingTvShowListFragment = TrendingTvShowListFragment.newInstance()
            attachFragments()
        } else {
            trendingMovieListFragment =
                supportFragmentManager.findFragmentByTag(TAG_MOVIE_LIST_FRAGMENT) as TrendingMovieListFragment
            trendingTvShowListFragment =
                supportFragmentManager.findFragmentByTag(TAG_TV_SHOW_LIST_FRAGMENT) as TrendingTvShowListFragment
        }
    }

    private fun attachFragments() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.movieContainer, trendingMovieListFragment, TAG_MOVIE_LIST_FRAGMENT)
            replace(R.id.tvShowContainer, trendingTvShowListFragment, TAG_TV_SHOW_LIST_FRAGMENT)
        }.commit()
    }

    companion object {
        internal const val TAG_MOVIE_LIST_FRAGMENT: String = "movieListFragment"
        internal const val TAG_TV_SHOW_LIST_FRAGMENT: String = "tvShowFragment"
    }
}
