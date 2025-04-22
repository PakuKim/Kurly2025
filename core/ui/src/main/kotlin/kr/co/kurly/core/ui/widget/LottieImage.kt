package kr.co.kurly.core.ui.widget

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieImage(
    modifier: Modifier,
    @RawRes lottieRes: Int,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(lottieRes)
    )

    val lottieAnimation = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimation.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }

    LottieAnimation(
        modifier = modifier,
        composition = composition,
         iterations = LottieConstants.IterateForever,
        contentScale = ContentScale.Fit,
    )
}