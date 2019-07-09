package com.sample.android.tmdb.ui.search

import android.os.Parcelable
import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.movie.MovieAdapter
import com.sample.android.tmdb.vo.TmdbItem
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_main.*

abstract class SearchBaseFragment<T : TmdbItem, E : Parcelable> : BaseFragment<T, E>() {

    override fun getSortType(): SortType? = null

    fun searchViewClicked(query: String?) {
        if (model.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as? MovieAdapter)?.submitList(null)
        }
    }

    override fun shouldIncrementEspressoIdlingResource() =
            (activity as SearchActivity).search_view.query.isNotEmpty()

    override fun getNavType(): NavType = (activity as SearchActivity).navType
}