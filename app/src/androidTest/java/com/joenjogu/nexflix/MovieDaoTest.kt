package com.joenjogu.nexflix

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.joenjogu.nexflix.data.MovieDao
import com.joenjogu.nexflix.data.MovieDatabase
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.utilities.getValue
import com.joenjogu.nexflix.utils.Category
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao

    private val movie1 = Movie(
        1,
        "https:// url",
        "https:// url",
        "Tenet",
        "time",
        2.4,
        "2020",
        Category.TopRated
    )
    private val movie2 = Movie(2, "https:// url","https:// url", "Joker", "jokes", 5.2, "2020", Category.TopRated)
    private val movie3 = Movie(
        3,
        "https:// url",
        "https:// url",
        "Crush",
        "banter",
        8.9,
        "2020",
        Category.Trending,
        1
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
        dao = database.movieDao

        dao.insertAllMovies(listOf(movie1, movie2, movie3))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAllMovies() {
        val movieList = getValue(dao.getAllMovies(Category.TopRated))
        assertThat(movieList.size, equalTo(2))
    }

//    @Test
//    fun testGetMovieById() {
//        val id = 2
//        runBlocking {
//            val value = dao.getMovieById(id)
//            assertThat(value.value?.title, equalTo("\"null\""))
//        }
//    }

    @Test
    fun testGetRecommendedMovies() {
        val id = 1
        runBlocking {
            val recommendation = getValue(dao.getRecommendedMovies(id))
            assertThat(recommendation[0].id, equalTo(3))
        }
    }
}
