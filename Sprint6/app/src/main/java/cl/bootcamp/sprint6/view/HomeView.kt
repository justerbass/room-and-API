package cl.bootcamp.sprint6.view

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cl.bootcamp.sprint6.R
import cl.bootcamp.sprint6.component.CardProdut
import cl.bootcamp.sprint6.component.TopBarComponent
import cl.bootcamp.sprint6.ui.theme.Color1

import cl.bootcamp.sprint6.viewModel.ProductViewModel

@Composable
fun HomeView(viewModel: ProductViewModel, navController: NavController){

    LaunchedEffect (Unit) {
        viewModel.getAllApi()
    }

    Scaffold (modifier = Modifier.fillMaxSize()
        ,topBar = {
            TopBarComponent(titulo = stringResource(id = R.string.home_title),
                mostrarBotton = false, onClick = {})

        })

    {innerPadding->

        val products by viewModel.product.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding).background(Color1)
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

