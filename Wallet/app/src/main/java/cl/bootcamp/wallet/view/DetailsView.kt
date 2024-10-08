package cl.bootcamp.wallet.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import cl.bootcamp.wallet.viewModel.WalletViewModel

@Composable
fun DetailsView(navController: NavController, viewModel: WalletViewModel, id: Int) {

    LaunchedEffect (Unit){
        viewModel.getUserById(id)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clean()
        }
    }
}