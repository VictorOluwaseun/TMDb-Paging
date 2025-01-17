package com.sample.android.tmdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.domain.*
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.detail.movie.DetailMovieViewModel
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var rxJavaRule: TestRule = TestRxJavaRule()

    @Mock
    private lateinit var api: MovieApi

    private lateinit var movie: Movie

    @Before
    fun setup() {
        movie = Movie(1, "", null, null, null, "", 1.1)
    }

    @Test
    fun loadTrailersAndCredits() {
        val trailers = VideoWrapper(listOf(Video("id", "", "", "", "")))
        val creditWrapper = CreditWrapper(listOf(Cast("", "", null, 1)), listOf())
        `when`(api.movieTrailers(anyInt())).thenReturn(Single.just(trailers))
        `when`(api.movieCredit(anyInt())).thenReturn(Single.just(creditWrapper))

        val viewModel = DetailMovieViewModel(api, movie)

        viewModel.liveData.value?.let {
            assertTrue(it.videos.size == 1)
            assertTrue(it.creditWrapper.cast.size == 1)
            assertTrue(it.creditWrapper.crew.isEmpty())
            assertTrue(it.videos[0].id == "id")
            assertTrue(it.creditWrapper.cast[0].id == 1)
        }
    }

    @Test
    fun errorLoadTrailers() {
        val creditWrapper = CreditWrapper(listOf(Cast("", "", null, 1)), listOf())
        `when`(api.movieTrailers(anyInt())).thenReturn(Single.error(Exception()))
        `when`(api.movieCredit(anyInt())).thenReturn(Single.just(creditWrapper))

        val viewModel = DetailMovieViewModel(api, movie)

        with(viewModel.liveData.value) {
            assertThat(this, `is`(nullValue()))
        }
    }

    @Test
    fun errorLoadCredits() {
        val trailers = VideoWrapper(listOf(Video("id", "", "", "", "")))
        `when`(api.movieTrailers(anyInt())).thenReturn(Single.just(trailers))
        `when`(api.movieCredit(anyInt())).thenReturn(Single.error(Exception()))

        val viewModel = DetailMovieViewModel(api, movie)

        with(viewModel.liveData.value) {
            assertThat(this, `is`(nullValue()))
        }
    }
}