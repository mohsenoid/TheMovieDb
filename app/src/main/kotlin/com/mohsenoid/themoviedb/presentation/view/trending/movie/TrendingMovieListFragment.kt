package com.mohsenoid.themoviedb.presentation.view.trending.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.base.BaseFragment
import com.mohsenoid.themoviedb.presentation.model.MovieModel
import com.mohsenoid.themoviedb.presentation.view.details.movie.MovieDetailsActivity
import kotlinx.android.synthetic.main.movie_list_fragment.list
import kotlinx.android.synthetic.main.movie_list_fragment.progress
import kotlinx.coroutines.launch
import org.koin.android.scope.lifecycleScope

class TrendingMovieListFragment : BaseFragment(), MovieListAdapter.ClickListener,
    TrendingMovieContract.View {

    private val presenter: TrendingMovieContract.Presenter by lifecycleScope.inject()

    private val adapter: MovieListAdapter = MovieListAdapter(listener = this)

    private var movies: ArrayList<MovieModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()

        if (savedInstanceState != null) {
            @Suppress("UNCHECKED_CAST")
            val movies: ArrayList<MovieModel>? =
                savedInstanceState.getSerializable(STATE_MOVIES) as? ArrayList<MovieModel>
            if (movies != null) setMoviesResult(movies)
        }

        if (movies == null) loadMovies()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list.adapter = adapter
    }

    private fun loadMovies() {
        launch {
            presenter.loadMovies()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.bind(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(STATE_MOVIES, movies)
    }

    override fun onDetach() {
        presenter.unbind()
        super.onDetach()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showOfflineMessage(isCritical: Boolean) {
        Toast.makeText(context, R.string.offline_app, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun setMoviesResult(result: List<MovieModel>) {
        movies = ArrayList(result)
        adapter.setModels(result)
        adapter.notifyDataSetChanged()
    }

    override fun onMovieClick(movie: MovieModel) {
        val intent = MovieDetailsActivity.newIntent(context, movie)
        startActivity(intent)
    }

    companion object {
        const val STATE_MOVIES = "stateMovies"

        fun newInstance(): TrendingMovieListFragment {
            return TrendingMovieListFragment()
        }
    }
}
