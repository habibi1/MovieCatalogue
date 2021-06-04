package com.project.moviecatalogue.ui.movie.list

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

class MovieFragmentTest {
    private val dummyMovie = DataDummy.generateDummyListMovie()

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
    fun loadMovie() {
        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.tv_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_status)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_durasi)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_rilis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularitas)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_bahasa)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_deskripsi)).check(matches(isDisplayed()))
    }
}