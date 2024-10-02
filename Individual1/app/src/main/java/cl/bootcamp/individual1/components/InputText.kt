package cl.bootcamp.individual1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InputText(
    hint: String,
    onValueChange: (String) -> Unit,
    value: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}