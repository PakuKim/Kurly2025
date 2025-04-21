package kr.co.kurly.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.co.kurly.core.ui.theme.MainColor
import kr.co.kurly.core.ui.theme.MainTheme

internal enum class MainBottomRoute(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val background: Color,
) {
    HOME("homeRoute", "홈", Icons.Default.Home, MainColor.background),
    ACTIVITY("activityRoute", "활동", Icons.Default.ShoppingCart, MainColor.white),
    PROFILE("profileRoute", "마이페이지", Icons.Default.Person, MainColor.white),
}

@Composable
internal fun MainRoute(
    viewModel: MainViewModel = hiltViewModel(),
    mainBuilder: NavGraphBuilder.() -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        mainBuilder = mainBuilder
    )
}

@Composable
private fun MainScreen(
    mainNavController: NavHostController = rememberNavController(),
    mainBuilder: NavGraphBuilder.() -> Unit
) {
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Scaffold(
            containerColor = MainBottomRoute.entries.find { currentRoute == it.route }
                ?.background ?: MainColor.white,
            bottomBar = {
                MainBottomScreen(
                    mainNavController = mainNavController
                )
            }
        ) { scaffoldPadding ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding),
                navController = mainNavController,
                startDestination = MainBottomRoute.HOME.route,
                builder = mainBuilder,
            )
        }
    }
}

@Composable
private fun MainBottomScreen(
    mainNavController: NavController,
) {
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            ),
        containerColor = MainColor.white,
        tonalElevation = 0.dp,
    ) {
        MainBottomRoute.entries.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    mainNavController.navigate(screen.route) {
                        popUpTo(mainNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label
                    )
                },
                label = {
                    Text(
                        text = screen.label,
                        style = MainTheme.typography.caption01B
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MainColor.subColor03,
                    selectedTextColor = MainColor.subColor03,
                    unselectedIconColor = MainColor.grey05,
                    unselectedTextColor = MainColor.grey03,
                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        LocalAbsoluteTonalElevation.current
                    )
                )
            )
        }
    }
}

@Preview
@Composable
private fun MainBottomScreenPreview() {
    MainTheme {
        MainBottomScreen(
            mainNavController = rememberNavController()
        )
    }
}