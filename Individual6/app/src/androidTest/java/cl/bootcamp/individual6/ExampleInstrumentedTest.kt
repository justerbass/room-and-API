package cl.bootcamp.individual6

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.bootcamp.individual6.view.HomeView
import cl.bootcamp.individual6.viewModel.MoviesViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun verifyMovieIsStoredInRoomOnAddButtonClick() = runBlocking {

        composeTestRule.onNodeWithTag("addMovieButton").performClick()

        composeTestRule.waitForIdle()

        val movies = moviesViewModel.loadMoviesFromDb()

        assertEquals(1, movies)
    }

    @Test
    fun verifyMovieCardHasDeleteButton() {

        composeTestRule.onNodeWithTag("singleMovieCard")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("deleteMovieButton")
            .assertExists()
            .assertIsDisplayed()
    }
}
