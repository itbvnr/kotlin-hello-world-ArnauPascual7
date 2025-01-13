package cat.itb.m78.exercices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*

@Composable
fun WelcomeApp() {
    Column(modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Welcome!", fontSize = 20.sp, color = Color.White)
        Text("Start learning now", color = Color.White)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {  }) { Text("Login") }
        Button(onClick = {  }) { Text("Register") }
    }
}