package anzhy.dizi.ktorapp.presentation.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import anzhy.dizi.ktorapp.presentation.components.RatingWidget
import anzhy.dizi.ktorapp.ui.theme.LARGE_PADDING
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    //collect as lazy paging items will collect values from flow of pagingData and will
    //represent that data inside lazy paging item instance
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        }
    ) {
        RatingWidget(
            modifier = Modifier.padding(all = LARGE_PADDING), rating = 3.3)
    }
}