package com.example.moodcheck.ui.navigation

// Interface to describe navigation destinations
interface NavigationDestination {
    // Unique name for composable paths
    val route: String

    // String resource id to that contains a title to be displayed
    val titleRes: Int
}