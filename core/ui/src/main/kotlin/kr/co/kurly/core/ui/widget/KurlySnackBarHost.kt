package kr.co.kurly.core.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.kurly.core.ui.theme.MainColor
import kr.co.kurly.core.ui.theme.MainTheme

@Composable
fun KurlySnackBarHost(
    snackBarHostState: SnackbarHostState,
    containerColor: Color = MainColor.error,
) {
    SnackbarHost(
        hostState = snackBarHostState
    ) { data ->
        KurlySnackBarScreen(
            title = data.visuals.message,
            containerColor = containerColor
        )
    }
}

@Composable
private fun KurlySnackBarScreen(
    title: String,
    containerColor: Color = MainColor.error,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            )
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .background(containerColor)
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MainTheme.typography.body01B,
            color = MainColor.white,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun KurlySnackBarScreenPreview() {
    MainTheme {
        Surface {
            KurlySnackBarScreen(
                title = "TEST"
            )
        }
    }
}