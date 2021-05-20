package com.dea.mymoviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Before
    fun setUp() {
        //ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_popular)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        onView(withId(R.id.rv_movies_now_playing)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_now_playing)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        onView(withId(R.id.scrollView_movies)).perform(swipeUp())
        onView(withId(R.id.rv_movies_top_rated)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_top_rated)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows_popular)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        onView(withId(R.id.rv_tv_shows_on_the_air)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows_on_the_air)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        onView(withId(R.id.scrollView_tv_shows)).perform(swipeUp())
        onView(withId(R.id.rv_tv_shows_top_rated)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows_top_rated)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
    }

    @Test
    fun loadFavMovie() {
        onView(withId(R.id.rv_movies_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(19, click()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.action_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.action_fav)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(19, click()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.action_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.action_fav)).perform(click())
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_fav_tv_show)).check(matches(isDisplayed()))
    }
}