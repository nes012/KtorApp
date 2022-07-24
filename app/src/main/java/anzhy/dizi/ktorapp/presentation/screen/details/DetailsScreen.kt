package anzhy.dizi.ktorapp.presentation.screen.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import anzhy.dizi.ktorapp.util.Constants.BASE_URL
import anzhy.dizi.ktorapp.util.PaletteGenerator.convertImageUrlToBitmap
import anzhy.dizi.ktorapp.util.PaletteGenerator.extractColorsFromBitmap


@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    //true = this mean this block will br triggered only 1st time we called details screen
    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collect() { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "${BASE_URL}${selectedHero?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }

}