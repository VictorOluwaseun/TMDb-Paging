package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.MOST_POPULAR
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PopularTVShowFragment @Inject
constructor() : TVShowFragment() {

    override fun getSortType(): SortType = MOST_POPULAR
}