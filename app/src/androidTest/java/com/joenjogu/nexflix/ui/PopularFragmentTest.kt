package com.joenjogu.nexflix.ui

import androidx.fragment.app.Fragment
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joenjogu.nexflix.utilities.createRule
import com.joenjogu.nexflix.viewmodels.PopularMovieViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module

//@RunWith(AndroidJUnit4ClassRunner::class)
//class PopularFragmentTest {
//
//    private val viewModel: PopularMovieViewModel = mockk(relaxed = true)
//    private val fragment = PopularFragment()
//    private val direction = ViewPagerFragmentDirections.actionViewPagerFragmentToMovieDetailFragment()
//
//    @get:Rule
//    val activityTestRule = createRule<Fragment>(
//        direction,
//        module {
//            single(override = true) {
//                viewModel
//            }
//        }
//    )
//
//    @Test
//    fun testBasicFunction() {
//    }
//}
