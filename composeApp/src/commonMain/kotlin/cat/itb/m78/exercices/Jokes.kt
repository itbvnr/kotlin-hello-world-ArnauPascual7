package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Joke(val id: Int, val type: String, val setup: String, val punchline: String)

class JokesViewModel : ViewModel() {
    val joke = mutableStateOf<Joke?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            joke.value = JokesApi.list().random()
        }
    }
    fun getJoke() {
        viewModelScope.launch(Dispatchers.Default) {
            joke.value = JokesApi.list().random()
        }
    }
}

object JokesApi {
    val url = "https://api.sampleapis.com/jokes/goodJokes"
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Joke>>()
}

@Composable
fun JokesScreen() {
    val viewModel = viewModel { JokesViewModel() }
    val joke = viewModel.joke.value
    var showPunchLine by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if (joke != null) {
            Text(joke.setup)
            if (showPunchLine) {
                Text(joke.punchline)
                Button(onClick={
                    viewModel.getJoke()
                    showPunchLine = false
                }) {
                    Text("Get a joke")
                }
            } else {
                Button(onClick = { showPunchLine = true } ) {
                    Text("Punch Line")
                }
            }
        }
        else {
            CircularProgressIndicator()
        }
    }
}