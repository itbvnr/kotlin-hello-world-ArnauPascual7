package cat.itb.m78.exercices

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun GoodMorningApp() {
    var text = remember { mutableStateOf("Good ?!") }
    Column {
        Text(text.value)
        Button(onClick = {
            text.value = "Good Morning!"
        }) {
            Text("Morning")
        }
        Button(onClick = {
            text.value = "Good Night!"
        }) {
            Text("Night")
        }
    }
}