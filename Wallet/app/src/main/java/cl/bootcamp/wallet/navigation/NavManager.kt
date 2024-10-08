package cl.bootcamp.wallet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.wallet.view.DetailsView
import cl.bootcamp.wallet.view.HomeView
import cl.bootcamp.wallet.viewModel.WalletViewModel

@Composable
fun NavManager(viewModel:WalletViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home") {

        composable("Home"){
            HomeView(viewModel = viewModel, navController = navController)
        }

        composable("DetailsView/{id}", arguments = listOf(
            navArgument("id"){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt("id") ?: 0
            DetailsView(viewModel = viewModel, navController = navController, id = id)
        }
    }
}