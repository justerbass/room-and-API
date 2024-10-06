package cl.bootcamp.individual3.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ShowText(
            text:String,
            title: Boolean,
            maxLines: Int){

    Text(
        text = text,
        fontWeight = if (title) FontWeight.Bold else FontWeight.Normal,
        maxLines = maxLines,
        fontSize = if (title) 24.sp else 16.sp,
        color = if (title) Color(0xffff4c4c) else Color.White, // Rojo para título, negro para texto normal
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black, // Sombra blanca para simular el borde
                offset = Offset(1.5f, 1f), // Ajuste de la sombra para el borde
                blurRadius = 3f // Ajusta el radio de difuminado para hacer más visible el borde
            )
        )
    )
}