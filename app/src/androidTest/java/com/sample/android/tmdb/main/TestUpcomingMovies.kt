package com.sample.android.tmdb.main

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.BaseMainActivity
import com.sample.android.tmdb.ui.paging.main.movie.UpcomingMoviesActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestUpcomingMovies : BaseMainActivity() {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<UpcomingMoviesActivity> =
        object : ActivityTestRule<UpcomingMoviesActivity>(
            UpcomingMoviesActivity::class.java
        ) {
            override fun getActivityIntent(): Intent {
                val targetContext: Context =
                    InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, UpcomingMoviesActivity::class.java)
            }
        }

    override val title: String
        get() = "Upcoming Movies"
}