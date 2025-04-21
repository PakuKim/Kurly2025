package kr.co.kurly.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kr.co.kurly.core.ui.theme.MainTheme
import kr.co.kurly.feature.main.navigation.MAIN_ROUTE
import kr.co.kurly.feature.main.navigation.mainGraph
import kr.co.kurly.MainViewModel

private enum class MainNavRoute(
    val route: String,
) {
    SPLASH("splash"),
    MAIN(MAIN_ROUTE)
}

@Composable
internal fun MainNavHost(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    var navRoute by remember { mutableStateOf(MainNavRoute.SPLASH) }

    LaunchedEffect(Unit) {
        delay(3_000L)
        navRoute = MainNavRoute.MAIN
    }

    MainNavHostScreen(
        startDestinationRoute = navRoute,
        navController = navController
    )
}

@Composable
private fun MainNavHostScreen(
    startDestinationRoute: MainNavRoute = MainNavRoute.SPLASH,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestinationRoute.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(MainNavRoute.SPLASH.route) {}

        mainGraph(
            navController = navController
        )
    }
}

@Preview(showSystemUi = false)
@Composable
private fun MainNavHostPreview() {
    MainTheme {
        MainNavHostScreen(
            navController = rememberNavController(),
        )
    }
}