package anzhy.dizi.ktorapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import anzhy.dizi.ktorapp.presentation.screen.search.SearchWidget
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    // composeRule allows execute and call composable fun in test environment
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Anzhyk")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("Anzhyk")
    }

}