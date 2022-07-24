package anzhy.dizi.ktorapp.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import anzhy.dizi.ktorapp.navigation.Screen
import anzhy.dizi.ktorapp.presentation.common.ListContent
import anzhy.dizi.ktorapp.ui.theme.statusBarColor
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    //collect as lazy paging items will collect values from flow of pagingData and will
    //represent that data inside lazy paging item instance
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.statusBarColor)

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        content = {
            ListContent(heroes = allHeroes, navController = navController)
        }
    )
}