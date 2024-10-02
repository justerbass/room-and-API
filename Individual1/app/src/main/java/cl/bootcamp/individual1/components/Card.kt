package cl.bootcamp.individual1.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


@Composable
fun ProfileCard(
                name: String,
                phoneNumber: String,
                email: String,
                profileImage: String,
                birthday: String,
                onClick: () -> Unit,
                deleteClick: () -> Unit
                )
{
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()

            ,
        onClick = onClick,
        colors = CardDefaults.cardColors(Color.Blue)
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End){
            IconButton(onClick =  deleteClick ) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = null)
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberAsyncImagePainter(profileImage),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp)),

            )

            Separation()

            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Separation()

            Text(
                text = phoneNumber,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Separation()

            Text(
                text = email,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Separation()

            Text(text = birthday,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }
}