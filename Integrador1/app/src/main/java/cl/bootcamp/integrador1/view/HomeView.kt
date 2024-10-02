package cl.bootcamp.integrador1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import cl.bootcamp.integrador1.component.LoadingCard
import cl.bootcamp.integrador1.component.Space
import cl.bootcamp.integrador1.viewModel.UserViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: UserViewModel
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Simple Rest API + Room ", color = Color.White)},
                actions = {
                    IconButton(onClick = {viewModel.addNewUser()} ){
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF8A65)
                )
            )
        })
    {
        ContentHomeView(it, viewModel)
    }
}

@Composable
fun ContentHomeView(paddingValues: PaddingValues, viewModel: UserViewModel){

    val users by viewModel.users.collectAsState(listOf())
    val isLoading by viewModel.isLoading.collectAsState(false)

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        var userCount = users.size
        if (isLoading) userCount++

        items(count = userCount){ index ->
            var auxIndex = index
            if (isLoading) {
                if (auxIndex == 0)
                    return@items LoadingCard()
                auxIndex--
            }
            val user = users[auxIndex]
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(1.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ){
                Row (modifier = Modifier.padding(8.dp)){
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter = rememberAsyncImagePainter(
                            model = user.thumbnail),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
                    Space()
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "${user.name} ${user.lastName}")
                        Text(text = "${user.city}, ${user.state}")
                    }
                    Space()
                    IconButton(onClick = {viewModel.deleteUser(user)}) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = null)
                    }
                }
            }
        }
    }
}