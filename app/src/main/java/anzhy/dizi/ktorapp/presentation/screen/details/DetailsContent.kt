package anzhy.dizi.ktorapp.presentation.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import anzhy.dizi.ktorapp.R
import anzhy.dizi.ktorapp.domain.model.Hero
import anzhy.dizi.ktorapp.presentation.components.InfoBox
import anzhy.dizi.ktorapp.presentation.components.OrderedList
import anzhy.dizi.ktorapp.ui.theme.*
import anzhy.dizi.ktorapp.util.Constants.ABOUT_TEXT_MAX_LINES
import anzhy.dizi.ktorapp.util.Constants.BASE_URL
import anzhy.dizi.ktorapp.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import coil.compose.AsyncImage
import coil.request.ImageRequest


@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        },
        content = {
            selectedHero?.let { it1 ->
                BackgroundContent(
                    heroImage = it1.image,
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING)
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_hearts),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor
            )

            Text(
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${heroImage}"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.placeholder)
                .build(),
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        //close icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(),
                onClick = { onCloseClicked() }) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "LALALA",
            image = "",
            about = "lalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\nlalala lalal ala l aksjdhjk alksjhdk ljh  lalala\n",
            rating = 4.5,
            power = 0,
            month = "Dec",
            day = "2nd",
            family = listOf("KAMAA", "ASKDJ", "askldj", "Ansdk", "ASNZH", "ANANNA"),
            abilities = listOf("mamam", "adadad", "asdcn", "qweqwe", "oioioi", "uuhuuuu"),
            natureTypes = listOf("Wind", "Earth")
        )
    )
}