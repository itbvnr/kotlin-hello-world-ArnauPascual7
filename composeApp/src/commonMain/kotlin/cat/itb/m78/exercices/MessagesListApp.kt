package cat.itb.m78.exercices

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesListApp() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("App Bar Title")},
                navigationIcon = {Icon(Icons.AutoMirrored.Filled.ArrowBack, "back")}) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}){
                Icon(Icons.Default.Add, "add")
            }
        }
    ) { paddingValues ->
        Column (Modifier.padding(paddingValues)){
            LazyColumn {
                items(messages) { message ->
                    Column(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color.LightGray).padding(15.dp)) {
                        Text(message.author)
                        Text(message.body)
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
    /*LazyColumn {
        messages.forEach { message ->
            item {
                Column(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color.LightGray).padding(15.dp)) {
                    Text(message.author)
                    Text(message.body)
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }*/
}