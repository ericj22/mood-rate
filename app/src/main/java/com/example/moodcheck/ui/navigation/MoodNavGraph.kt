package com.example.moodcheck.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moodcheck.ui.rate.RateDestination
import com.example.moodcheck.ui.rate.RateMoodScreen
import com.example.moodcheck.ui.years.YearDestination
import com.example.moodcheck.ui.years.YearScreen

@Composable
fun MoodNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = YearDestination.route,
        modifier = modifier
    ) {
        composable(route = YearDestination.route) {
            YearScreen(
                navigateToRateMood = { navController.navigate(RateDestination.route) }
            )
        }
        composable(route = RateDestination.route) {
            RateMoodScreen()
        }
    }
}
