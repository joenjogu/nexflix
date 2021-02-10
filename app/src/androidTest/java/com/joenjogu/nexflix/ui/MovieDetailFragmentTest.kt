package com.joenjogu.nexflix.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joenjogu.nexflix.viewmodels.MovieDetailViewModel
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    private val fragment: MovieDetailFragment = mockk(relaxed = true)
    private val viewmodel = MovieDetailViewModel()

}