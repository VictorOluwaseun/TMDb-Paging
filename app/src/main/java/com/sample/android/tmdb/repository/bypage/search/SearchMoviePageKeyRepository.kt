package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val dataSource: RemoteDataSource)
    : PageKeyRepository<Movie, ItemApi.MovieWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie, ItemApi.MovieWrapper> =
            SearchMovieDataSourceFactory(dataSource = dataSource,
                    query = query,
                    retryExecutor = retryExecutor)
}