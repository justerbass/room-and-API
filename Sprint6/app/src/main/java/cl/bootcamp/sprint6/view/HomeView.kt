package cl.bootcamp.sprint6.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.bootcamp.sprint6.component.CardProdut
import cl.bootcamp.sprint6.component.TopBarComponent

import cl.bootcamp.sprint6.viewModel.ProductViewModel

@Composable
fun HomeView(viewModel: ProductViewModel, navController: NavController){

    LaunchedEffect (Unit) {
        viewModel.getAllApi()
    }

    Scaffold (modifier = Modifier.fillMaxSize()
        , topBar = {TopBarComponent(
                titulo = "Home",
                mostrarBotton = false,
                onClick = {}
            )
        })

    {innerPadding->

        val products by viewModel.product.collectAsState(initial = listOf())



        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(products) { item ->
                CardProdut (
                    item.name,
                    item.price,
                    item.image,
                ) {
                    navController.navigate("DetailsView/${item.id}")
                }
            }
        }

    }


}

