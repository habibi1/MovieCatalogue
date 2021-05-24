package com.project.moviecatalogue.ui.tvshow.list

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

class TvShowFragmentTest {
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadTvShow() {
        onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_show)).perform(click())
        if (dummyTvShow.isEmpty()) {
            onView(withId(R.id.layout_data_kosong)).check(matches(isDisplayed()))
        } else {
            onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
            onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
        }
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_show)).perform(click())
        if (dummyTvShow.isEmpty()) {
            onView(withId(R.id.layout_data_kosong)).check(matches(isDisplayed()))
        } else {
            onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.tv_title_tv_show)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_title_tv_show)).check(matches(withText(dummyTvShow[0].name)))
            onView(withId(R.id.edt_genre)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_genre)).check(matches(withText(dummyTvShow[0].genreIds)))
            onView(withId(R.id.edt_durasi)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_durasi)).check(matches(withText(dummyTvShow[0].durasi)))
            onView(withId(R.id.edt_rilis)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_rilis)).check(matches(withText(dummyTvShow[0].firstAirDate)))
            onView(withId(R.id.edt_jumlah_episode)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_jumlah_episode)).check(matches(withText(dummyTvShow[0].jumlahEpisode)))
            onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_rating)).check(matches(withText(dummyTvShow[0].voteAverage.toString())))
            onView(withId(R.id.tv_popularitas)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_popularitas)).check(matches(withText(dummyTvShow[0].popularity.toString())))
            onView(withId(R.id.tv_vote)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_vote)).check(matches(withText(dummyTvShow[0].voteCount.toString())))
            onView(withId(R.id.tv_bahasa)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_bahasa)).check(matches(withText(dummyTvShow[0].originalLanguage)))
            onView(withId(R.id.tv_deskripsi)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_deskripsi)).check(matches(withText(dummyTvShow[0].overview)))
        }
    }
}