package cl.bootcamp.wallet.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.bootcamp.wallet.viewModel.WalletViewModel

@Composable
fun HomeView(viewModel: WalletViewModel, navController: NavController) {

    LaunchedEffect(Unit) {
        viewModel.getALLAPI()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        innerPadding ->
        val users by viewModel.user.collectAsState(listOf())

        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(users){ item->
                Text(text = item.nombre)
            }
        }
    }
}