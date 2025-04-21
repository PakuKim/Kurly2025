package kr.co.kurly.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kr.co.kurly.feature.main.MainBottomRoute
import kr.co.kurly.feature.main.MainRoute

const val MAIN_ROUTE = "mainRoute"

fun NavController.navigateToMain(navOptions: NavOptions? = null) = navigate(MAIN_ROUTE, navOptions)

fun NavGraphBuilder.mainGraph(
    navController: NavController
) {
    composable(
        route = MAIN_ROUTE
    ) {
        MainRoute(
            mainBuilder = {
                composable(
                    route = MainBottomRoute.HOME.route
                ) {

                }

                composable(
                    route = MainBottomRoute.ACTIVITY.route
                ) {
                }

                composable(
                    route = MainBottomRoute.PROFILE.route
                ) {
                }
            }
        )
    }
}