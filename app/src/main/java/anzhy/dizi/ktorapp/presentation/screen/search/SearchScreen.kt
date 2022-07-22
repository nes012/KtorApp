package anzhy.dizi.ktorapp.presentation.screen.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun SearchScreen() {
    Scaffold(
        topBar = {
            SearchTopBar(
                text = "",
                onTextChange = {},
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
    ) {
        
    }
}