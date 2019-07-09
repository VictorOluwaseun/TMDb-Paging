package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.vo.Movie

class MoviesDataSourceFactory(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String) : ItemDataSourceFactory<Movie, ItemApi.MovieWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper> =
            PageKeyedMovieDataSource(dataSource = dataSource,
                    sortType = sortType,
                    query = query)
}