package cat.itb.m78.exercices.part1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle

@Composable
fun HelloWorldApp() {
    Text("Hello World", color = Color.Red, fontStyle = FontStyle.Italic)
}