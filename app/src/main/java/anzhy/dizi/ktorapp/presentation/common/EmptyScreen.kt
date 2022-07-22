package anzhy.dizi.ktorapp.presentation.common

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import anzhy.dizi.ktorapp.R
import anzhy.dizi.ktorapp.ui.theme.DarkGray
import anzhy.dizi.ktorapp.ui.theme.LightGray
import anzhy.dizi.ktorapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import anzhy.dizi.ktorapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }
    val icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnim, icon = icon, message = message)

}

@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.network_error_icon),
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    Log.d("parseErrorMessage", error.error.message.toString())
    return when {
        error.error is SocketTimeoutException -> {
            "Server Unavailable."
        }
        error.error is ConnectException -> {
            "Internet Unavailable."
        }
        else -> {
            "Unknown Error"
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyScreenPreview() {
    EmptyContent(alphaAnim = ContentAlpha.disabled, icon = R.drawable.ic_network_error, message = "Internet Unavailable")
}