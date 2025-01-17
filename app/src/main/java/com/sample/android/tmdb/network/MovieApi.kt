package com.sample.android.tmdb.network

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.domain.VideoWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(): ItemWrapper<Movie>

    @GET("3/movie/popular")
    suspend fun popularMovies(): ItemWrapper<Movie>

    @GET("3/movie/upcoming")
    suspend fun latestMovies(): ItemWrapper<Movie>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(): ItemWrapper<Movie>

    @GET("3/trending/movie/day")
    fun trendingMovies(@Query("page") page: Int): Observable<ItemWrapper<Movie>>

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularMovies(@Query("page") page: Int): Observable<ItemWrapper<Movie>>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun topRatedMovies(@Query("page") page: Int): Observable<ItemWrapper<Movie>>

    @GET("3/movie/upcoming?language=en")
    fun latestMovies(@Query("page") page: Int): Observable<ItemWrapper<Movie>>

    @GET("3/search/movie?language=en")
    fun searchItems(@Query("page") page: Int, @Query("query") query: String): Observable<ItemWrapper<Movie>>

    @GET("3/movie/{movieId}/videos")
    fun movieTrailers(@Path("movieId") movieId: Int): Single<VideoWrapper>

    @GET("3/movie/{movieId}/credits")
    fun movieCredit(@Path("movieId") movieId: Int): Single<CreditWrapper>
}