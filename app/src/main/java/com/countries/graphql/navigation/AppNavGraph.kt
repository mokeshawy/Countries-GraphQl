package com.countries.graphql.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.countries.graphql.features.country_details_screen.presentation.DetailedCountryScreen
import com.countries.graphql.features.home_screen.presentation.HomeScreen
import com.countries.graphql.features.splash_screen.presentation.SplashScreen
import com.countries.graphql.navigation.route.AppNavGraphRoutes

@Composable
fun AppNavGraph(
    navController: NavHostController,
    isNetworkAvailable: Boolean?
) {
    NavHost(
        navController = navController,
        startDestination = AppNavGraphRoutes.SplashScreen.route
    ) {

        //SplashScreen
        composable(AppNavGraphRoutes.SplashScreen.route) {
            SplashScreen {
                navController.navigate(route = AppNavGraphRoutes.HomeScreen.route) {
                    popUpTo(navController.graph.id)
                }
            }
        }

        //HomeScreen
        composable(AppNavGraphRoutes.HomeScreen.route) {
            HomeScreen(isNetworkAvailable) { code ->
                navController.navigate("${AppNavGraphRoutes.DetailedCountryScreen.route}/$code")
            }
        }

        composable(
            route = "${AppNavGraphRoutes.DetailedCountryScreen.route}/{code}", arguments = listOf(
                navArgument("code") { type = NavType.StringType })
        ) {
            DetailedCountryScreen()
        }
    }
}