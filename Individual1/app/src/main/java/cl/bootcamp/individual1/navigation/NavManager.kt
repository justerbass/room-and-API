package cl.bootcamp.individual1.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.individual1.view.AddProfile
import cl.bootcamp.individual1.view.HomeView
import cl.bootcamp.individual1.viewModel.ContactsViewModel

@Composable
fun NavManager(){
    val navController = rememberNavController()
    val viewModel : ContactsViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeView(navController = navController, viewModel)
        }
        composable("add"){
            AddProfile(navController = navController, viewModel)
        }
    }
}