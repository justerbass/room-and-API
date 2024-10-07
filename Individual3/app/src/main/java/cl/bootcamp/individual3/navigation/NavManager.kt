package cl.bootcamp.individual3.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.individual3.view.DetailsNews
import cl.bootcamp.individual3.view.HomeView
import cl.bootcamp.individual3.viewmodel.NewsViewModel

@Composable
fun NavManager(viewModel: NewsViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home"){
            HomeView(navController, viewModel)
        }

        composable("DetailView/{id}",
            arguments = listOf(
            navArgument("id") { type = NavType.StringType },
        )
        ) {

            val id = it.arguments?.getString("id") ?:""

            DetailsNews(navController, viewModel, id)
        }
    }
}