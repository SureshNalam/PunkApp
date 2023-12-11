package com.example.punkapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.punkapp.presentation.screens.beerdetails.BeerDetailsScreen
import com.example.punkapp.presentation.screens.beersList.BeersScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.BEERS_LIST.route) {
        composable(route = NavRoute.BEERS_LIST.route) {
            BeersScreen(navController)
        }
        composable(route = NavRoute.BEER_DETAILS.route + "/{beerId}") { navBackStackEntry ->
            val selectedBeerId = navBackStackEntry.arguments?.getString("beerId")
            BeerDetailsScreen(selectedBeerId = selectedBeerId?.toInt() ?: 1, navController = navController)
        }
    }
}


enum class NavRoute(val route: String) {
    BEERS_LIST("beers"),
    BEER_DETAILS("beer_details")
}