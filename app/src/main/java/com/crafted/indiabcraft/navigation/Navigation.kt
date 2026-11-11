package com.crafted.indiabcraft.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.crafted.indiabcraft.ui.home.HomeScreen
import com.crafted.indiabcraft.ui.livetv.LiveTVScreen
import com.crafted.indiabcraft.ui.movies.MoviesScreen
import com.crafted.indiabcraft.ui.sports.SportsScreen
import com.crafted.indiabcraft.ui.profile.ProfileScreen
import com.crafted.indiabcraft.ui.player.VideoPlayerActivity
import com.crafted.indiabcraft.ui.search.SearchActivity
import com.crafted.indiabcraft.ui.settings.SettingsActivity
import com.crafted.indiabcraft.ui.detail.ContentDetailScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object LiveTV : Screen("live_tv")
    object Movies : Screen("movies")
    object Sports : Screen("sports")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{contentId}") {
        fun createRoute(contentId: String) = "detail/$contentId"
    }
    object Player : Screen("player/{videoUrl}") {
        fun createRoute(videoUrl: String) = "player/$videoUrl"
    }
    object Search : Screen("search")
    object Settings : Screen("settings")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        
        composable(Screen.LiveTV.route) {
            LiveTVScreen(navController = navController)
        }
        
        composable(Screen.Movies.route) {
            MoviesScreen(navController = navController)
        }
        
        composable(Screen.Sports.route) {
            SportsScreen(navController = navController)
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("contentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contentId = backStackEntry.arguments?.getString("contentId") ?: return@composable
            ContentDetailScreen(
                contentId = contentId,
                navController = navController
            )
        }
    }
}