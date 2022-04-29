package com.example.pedroscott

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.pedroscott.features.UserUiModel
import com.example.pedroscott.features.main.ErrorPreview
import com.example.pedroscott.features.main.LoadingPreview
import com.example.pedroscott.features.main.UserProfileCard
import com.example.pedroscott.ui.theme.CodeChallengeTheme
import org.junit.Rule
import org.junit.Test

class MainActivityKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_error_screen() {
        // Start the app
        composeTestRule.setContent {
            CodeChallengeTheme { ErrorPreview() }
        }
        composeTestRule.onNodeWithText("On No!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Is a super ERROR!").assertIsDisplayed()
    }

    @Test
    fun test_loading_screen() {
        // Start the app
        composeTestRule.setContent {
            CodeChallengeTheme { LoadingPreview() }
        }
        composeTestRule.onNodeWithTag("Loading").assertIsDisplayed()
    }

    @Test
    fun test_success_screen() {
        // Start the app
        composeTestRule.setContent {
            CodeChallengeTheme {
                UserProfileCard(
                    user = UserUiModel(
                        uid = "1",
                        avatarUrl = "",
                        firstName = "Pedro",
                        lastName = "Scott",
                        jobDescription = "Android developer"
                    )
                )
            }
        }
        composeTestRule.onNodeWithText("Hello Pedro Scott!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Job Description: Android developer").assertIsDisplayed()
    }

}