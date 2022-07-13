package anzhy.dizi.ktorapp.presentation.screen.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.google.accompanist.pager.*

@ExperimentalPagerApi
@Composable
fun HomeScreen(
) {

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        }
    ) {}

}