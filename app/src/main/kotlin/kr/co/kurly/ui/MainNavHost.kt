package kr.co.kurly.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kr.co.kurly.core.ui.theme.MainTheme
import kr.co.kurly.feature.main.navigation.MAIN_ROUTE
import kr.co.kurly.feature.main.navigation.mainGraph

private enum class MainNavRoute(
    val route: String,
) {
    MAIN(MAIN_ROUTE)
}

@Composable
internal fun MainNavHost(
    navController: NavHostController = rememberNavController(),
) {
    var navRoute by remember { mutableStateOf(MainNavRoute.MAIN) }

    MainNavHostScreen(
        startDestinationRoute = navRoute,
        navController = navController
    )
}

@Composable
private fun MainNavHostScreen(
    startDestinationRoute: MainNavRoute = MainNavRoute.MAIN,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestinationRoute.route,
    ) {
        mainGraph()
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