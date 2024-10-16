package cl.bootcamp.individual6



import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun verifyMovieIsStoredInRoomOnAddButtonClick() = runBlocking {

        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText(  "original_title The Great Adventure poster_path https://example.com/images/great_adventure.jpg release_date 2023-07-21").assertExists()

    }

    @Test
    fun verifyMovieCardHasDeleteButton() {

        composeTestRule.onNodeWithContentDescription("Delete").performClick()
    }

}
