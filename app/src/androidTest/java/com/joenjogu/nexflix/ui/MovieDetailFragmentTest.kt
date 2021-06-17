package com.joenjogu.nexflix.ui

import androidx.fragment.app.Fragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joenjogu.nexflix.utilities.createRule
import com.joenjogu.nexflix.viewmodels.MovieDetailViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    private val viewModel: MovieDetailViewModel = mockk(relaxed = true)
    private val fragment = MovieDetailFragment()
    private val direction = ViewPagerFragmentDirections.actionViewPagerFragmentToMovieDetailFragment()

    @get:Rule
    val fragmentRule = createRule<Fragment>(
        direction,
        module {
            single(override = true) {
                viewModel
            }
        }
    )

    @Test
    fun testBasicFunction() {
        onView(withText("Recommended Movies")).check(matches(isDisplayed()))
    }
}
