package com.countries.graphql.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.countries.graphql.features.home_screen.presentation.HomeScreen
import com.countries.graphql.features.splash_screen.presentation.SplashScreen
import com.countries.graphql.navigation.route.AppNavGraphRoutes

@Composable
fun AppNavGraph(isNetworkAvailable: Boolean?) {
    val navController = rememberNavController()
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
            HomeScreen(isNetworkAvailable) {

            }
        }
    }
}