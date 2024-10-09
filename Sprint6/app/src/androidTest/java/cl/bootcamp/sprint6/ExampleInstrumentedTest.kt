package cl.bootcamp.sprint6

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.bootcamp.sprint6.navigation.NavManager
import cl.bootcamp.sprint6.view.HomeView
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule =
        createAndroidComposeRule<MainActivity>()

    @Test
    fun agregarItemCorrectamente(){
        composeTestRule.activity.setContent{
            NavManager()
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.home_title))
            .assertIsDisplayed()
    }
}
