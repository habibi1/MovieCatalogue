package com.project.moviecatalogue.ui.tvshow.list

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.project.moviecatalogue.R
import com.project.moviecatalogue.ui.home.HomeActivity
import com.project.moviecatalogue.utils.DataDummy
import com.project.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class TvShowFragmentTest {
    private val dummyTvShow = DataDummy.generateDummyListTvShow()

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
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_status)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_rilis)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_jumlah_episode)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularitas)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_bahasa)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_deskripsi)).check(matches(isDisplayed()))
    }
}