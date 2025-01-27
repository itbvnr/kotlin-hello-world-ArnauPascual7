package cat.itb.m78.exercices

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*

@Composable
fun SayHelloScreenApp() {
    var show by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TextField(text,
            label = { Text("Name") },
            onValueChange = { text = it },
            shape = RoundedCornerShape(10.dp))
        Button(onClick = {
            show = true
        }) {
            Text("SayHello")
        }
    }
    if (show) {
        AlertDialog(
            onDismissRequest = { show = false },
            confirmButton = {},
            title = {Text("HELLO $text")}
        )
    }
}