package com.project.moviecatalogue.ui.tvshow.list

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.project.moviecatalogue.R
import com.project.moviecatalogue.ui.home.HomeActivity
import com.project.moviecatalogue.utils.DataDummy
import com.project.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {
    private val dummyTvShow = DataDummy.generateDummyListTvShow()
    private val dummyDetailTvShow = DataDummy.generateDummyDetailTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

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
            onView(withId(R.id.tv_title_tv_show)).check(matches(withText(dummyDetailTvShow.name)))
            onView(withId(R.id.edt_status)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_status)).check(matches(withText(dummyDetailTvShow.status)))
            onView(withId(R.id.edt_rilis)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_rilis)).check(matches(withText(dummyDetailTvShow.firstAirDate)))
            onView(withId(R.id.edt_jumlah_episode)).check(matches(isDisplayed()))
            onView(withId(R.id.edt_jumlah_episode)).check(matches(withText(dummyDetailTvShow.numberOfEpisodes.toString())))
            onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_rating)).check(matches(withText(dummyDetailTvShow.voteAverage.toString())))
            onView(withId(R.id.tv_popularitas)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_popularitas)).check(matches(withText(dummyDetailTvShow.popularity.toString())))
            onView(withId(R.id.tv_vote)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_vote)).check(matches(withText(dummyDetailTvShow.voteCount.toString())))
            onView(withId(R.id.tv_bahasa)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_bahasa)).check(matches(withText(dummyDetailTvShow.originalLanguage)))
            onView(withId(R.id.tv_deskripsi)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_deskripsi)).check(matches(withText(dummyDetailTvShow.overview)))
        }
    }
}