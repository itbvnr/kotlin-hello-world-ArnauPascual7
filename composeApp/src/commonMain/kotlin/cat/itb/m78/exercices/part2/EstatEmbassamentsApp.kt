package cat.itb.m78.exercices.part2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Estat(
    val dia: String,
    val estaci: String,
    val nivell_absolut: String,
    val percentatge_volum_embassat: String,
    val volum_embassat: String
)

class EstatViewModel : ViewModel() {
    var estats by mutableStateOf<List<Estat>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            estats = EstatApi.estats()
        }
    }
}

object EstatApi {
    val url = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json"
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun estats() = client.get(url).body<List<Estat>>()
}

@Composable
fun EstatEmbassamentsApp() {

}