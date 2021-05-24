package com.project.moviecatalogue.ui.movie.list

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.project.moviecatalogue.R
import com.project.moviecatalogue.ui.home.HomeActivity
import com.project.moviecatalogue.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest {
    private val dummyMovie = DataDummy.generateDummyMovie()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())
        if (dummyMovie.isEmpty()) {
            onView(withId(R.id.layout_data_kosong)).check(matches(isDisplayed()))
        } else {
            onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
            onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
        }
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())
        if (dummyMovie.isEmpty()) {
            onView(withId(R.id.layout_data_kosong)).check(matches(isDisplayed()))
        } else {
            onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.tv_title_movie)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_title_movie)).check(matches(withText(dummyMovie[0].name)))
            onView(withId(R.id.edt_genre)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_genre)).check(matches(withText(dummyMovie[0].genreIds)))
            onView(withId(R.id.edt_durasi)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_durasi)).check(matches(withText(dummyMovie[0].durasi)))
            onView(withId(R.id.edt_rilis)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_rilis)).check(matches(withText(dummyMovie[0].firstAirDate)))
            onView(withId(R.id.edt_usia)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_usia)).check(matches(withText(dummyMovie[0].usia)))
            onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_rating)).check(matches(withText(dummyMovie[0].voteAverage.toString())))
            onView(withId(R.id.tv_popularitas)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_popularitas)).check(matches(withText(dummyMovie[0].popularity.toString())))
            onView(withId(R.id.tv_vote)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_vote)).check(matches(withText(dummyMovie[0].voteCount.toString())))
            onView(withId(R.id.tv_bahasa)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_bahasa)).check(matches(withText(dummyMovie[0].originalLanguage)))
            onView(withId(R.id.tv_deskripsi)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_deskripsi)).check(matches(withText(dummyMovie[0].overview)))
        }
    }
}