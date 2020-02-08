package com.mohsenoid.themoviedb.presentation.view.trending.tvshow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohsenoid.themoviedb.R
import com.mohsenoid.themoviedb.presentation.base.BaseFragment
import com.mohsenoid.themoviedb.presentation.model.TvShowModel
import com.mohsenoid.themoviedb.presentation.view.details.tvshow.TvShowDetailsActivity
import kotlinx.android.synthetic.main.tv_show_list_fragment.list
import kotlinx.android.synthetic.main.tv_show_list_fragment.progress
import kotlinx.coroutines.launch
import org.koin.android.scope.lifecycleScope

class TrendingTvShowListFragment : BaseFragment(), TvShowListAdapter.ClickListener,
    TrendingTvShowContract.View {

    private val presenter: TrendingTvShowContract.Presenter by lifecycleScope.inject()

    private val adapter: TvShowListAdapter = TvShowListAdapter(listener = this)

    private var tvShows: ArrayList<TvShowModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_show_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()

        if (savedInstanceState != null) {
            @Suppress("UNCHECKED_CAST")
            val tvShows: ArrayList<TvShowModel>? =
                savedInstanceState.getSerializable(STATE_TV_SHOWS) as? ArrayList<TvShowModel>
            if (tvShows != null) setTvShowsResult(tvShows)
        }

        if (tvShows == null) loadTvShows()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list.adapter = adapter
    }

    private fun loadTvShows() {
        launch {
            presenter.loadTvShows()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.bind(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(STATE_TV_SHOWS, tvShows)
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

    override fun setTvShowsResult(result: List<TvShowModel>) {
        tvShows = ArrayList(result)
        adapter.setTvShows(result)
        adapter.notifyDataSetChanged()
    }

    override fun onTvShowClick(tvShow: TvShowModel) {
        val intent = TvShowDetailsActivity.newIntent(context, tvShow)
        startActivity(intent)
    }

    companion object {
        const val STATE_TV_SHOWS = "stateTvShows"

        fun newInstance(): TrendingTvShowListFragment {
            return TrendingTvShowListFragment()
        }
    }
}
