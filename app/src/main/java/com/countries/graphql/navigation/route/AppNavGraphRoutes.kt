package com.countries.graphql.navigation.route

sealed class AppNavGraphRoutes(val route : String) {

    data object  SplashScreen : AppNavGraphRoutes("SplashScreen")

    data object  HomeScreen : AppNavGraphRoutes("HomeScreen")
}