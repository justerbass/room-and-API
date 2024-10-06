package cl.bootcamp.individual3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import cl.bootcamp.individual3.navigation.NavManager
import cl.bootcamp.individual3.ui.theme.Individual3Theme
import cl.bootcamp.individual3.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : NewsViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            Individual3Theme {
                    NavManager(viewModel)
            }
        }
    }
}

