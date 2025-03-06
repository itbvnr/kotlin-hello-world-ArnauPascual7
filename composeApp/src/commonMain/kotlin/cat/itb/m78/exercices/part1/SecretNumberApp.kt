package cat.itb.m78.exercices.part1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import kotlin.random.*

@Composable
fun SecretNumberApp() {
    val secret = Random.nextInt(100)
    var input by remember { mutableStateOf("") }
    var attemps by remember { mutableStateOf(0) }
    var text by remember { mutableStateOf("Introdueix un número") }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Endevina el número", fontSize = 20.sp)
        TextField(input,
            onValueChange = { input = it },
            shape = RoundedCornerShape(10.dp))
        Button(onClick = {
            attemps += 1
            if (input.toIntOrNull() != null) {
                if (input.toInt() > secret) {
                    text = "El número que busques és més petit"
                }
                else if (input.toInt() < secret) {
                    text = "El número que busques és més gran"
                }
                else {
                    text = "Has encertat"
                }
            } else {
                text = "Introdueix un número"
            }
        }) {
            Text("Validar")
        }
        Text("Intents: $attemps")
        Text(text)
    }
}