package cat.itb.m78.exercices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*

data class Message(val author: String, val body: String)
val names = listOf(
    "Ellison Curry",
    "Briggs Willis",
    "Alexa Murphy",
    "Cameron Berry",
    "Annabelle Villarreal",
    "Nikolai Wiley",
    "Lauryn Morrow",
    "Kyree Hardy",
    "Jessica Lang",
    "Wells Wilson",
    "Luna Foster",
    "Kayden Taylor",
    "Sofia Mann",
    "Nehemiah Randall",
    "Christina Gordon",
    "Karter Kramer",
    "Hanna Morales",
    "Aaron Velez",
    "Megan Delarosa",
    "Osiris Johnson",
    "Emma Atkins",
    "Cason McKee",
    "Kori Walls",
    "Larry Shepherd",
)
val body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vestibulum nunc."
val messages = List(100){
    Message(names.random(), body)
}

@Composable
fun MessagesListApp() {
    LazyColumn(modifier = Modifier.width(300.dp).clip(RoundedCornerShape(10.dp)).background(Color.LightGray).padding(10.dp)) {
        items(messages) {index ->
            Text(index.author)
            Text(index.body)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}