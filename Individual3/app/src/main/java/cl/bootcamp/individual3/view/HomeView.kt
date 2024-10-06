package cl.bootcamp.individual3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.individual3.component.Separate
import cl.bootcamp.individual3.component.ShowImageUrl
import cl.bootcamp.individual3.component.ShowText
import cl.bootcamp.individual3.model.Article
import cl.bootcamp.individual3.navigation.Screnn
import cl.bootcamp.individual3.viewmodel.NewsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: NewsViewModel) {
    val news by viewModel.news.collectAsState()
    Scaffold (topBar ={
        CenterAlignedTopAppBar(
            title = { Text(text = "newsapi.org",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56c1ff))

            )
    }
    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .background(Color(0xFF1b263b))
            .fillMaxSize()) {

            if (news.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(news) { article ->
                        NewsItem(article, navController)
                    }
                }
            }
        }

    }


}

@Composable
fun NewsItem(article: Article, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(0xFF1b263b)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {navController.navigate(Screnn.DetailsScrenn.route)}
    ) {
        Box (modifier = Modifier.background(Color(0xffa8a8a8))){
            article.urlToImage?.let { ShowImageUrl(url = it) }
            Column(modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                ShowText(text = article.source.name, title = true, maxLines = 2)

                Column(modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom) {
                    ShowText(text = article.author ?: "", title = false, maxLines = 1)
                    Separate(value = 8)
                    ShowText(text = article.title, title = false, maxLines = 1)
                    Separate(value = 8)
                }

            }
        }
    }
}