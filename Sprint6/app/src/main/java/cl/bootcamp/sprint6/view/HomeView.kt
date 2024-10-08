package cl.bootcamp.sprint6.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import cl.bootcamp.sprint6.viewModel.ProductViewModel

@Composable
fun HomeView(viewModel: ProductViewModel, navController: NavController){

    LaunchedEffect (Unit) {
        viewModel.getAllApi()
    }

}