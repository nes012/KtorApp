package anzhy.dizi.ktorapp.presentation.screen.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                               searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {},
                onCloseClicked = {
                    //popBackStack will just remove our search screen from backstack and we're going to see screen which was
                    //behind this screen(Home Screen)
                    navController.popBackStack()
                }
            )
        }
    ) {

    }
}