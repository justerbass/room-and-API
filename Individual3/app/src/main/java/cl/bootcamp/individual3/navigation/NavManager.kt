package cl.bootcamp.individual3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.individual3.model.Article
import cl.bootcamp.individual3.view.DetailsNews
import cl.bootcamp.individual3.view.HomeView
import cl.bootcamp.individual3.viewmodel.NewsViewModel

@Composable
fun NavManager(viewModel: NewsViewModel){
    val navController = rememberNavController()



    NavHost(navController = navController, startDestination = Screnn.HomeScrenn.route) {

        composable(Screnn.HomeScrenn.route){
            HomeView(navController, viewModel)
        }

        composable(Screnn.DetailsScrenn.route, arguments = listOf(
            navArgument("id") { type = NavType.StringType },
            navArgument("name") { type = NavType.StringType }
        )){
            val id = it.arguments?.getString("id") ?: ""
            val name = it.arguments?.getString("name") ?: ""

            DetailsNews(navController, viewModel, id, name)
        }
    }
}