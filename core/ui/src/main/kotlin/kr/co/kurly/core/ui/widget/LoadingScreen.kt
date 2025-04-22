package kr.co.kurly.core.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.zIndex
import kr.co.kurly.core.ui.R

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        content()

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.7f)
            ) {
                LottieImage(
                    modifier = Modifier
                        .zIndex(10f)
                        .align(Alignment.Center),
                    lottieRes = R.raw.lottie_loading,
                )
            }
        }
    }
}