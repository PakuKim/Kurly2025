package kr.co.kurly.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.kurly.feature.main.MainRoute

const val MAIN_ROUTE = "mainRoute"

fun NavGraphBuilder.mainGraph() {
    composable(
        route = MAIN_ROUTE
    ) {
        MainRoute()
    }
}