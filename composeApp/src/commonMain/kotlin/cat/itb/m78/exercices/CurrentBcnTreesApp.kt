package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Tree(
    @SerialName("nom_cientific") val name: String,
    @SerialName("codi") val code: String
)

object TreesApi {
    private val url = "https://fp.mateuyabar.com/DAM-M03/UF3/exercicis/files/OD_Arbrat_Zona_BCN.json"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun trees() = client.get(url).body<List<Tree>>()
}

class TreesViewModel : ViewModel() {
    var tree by mutableStateOf<Tree?>(null)
    var treesSize by mutableStateOf(0)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            tree = TreesApi.trees().random()
            treesSize = TreesApi.trees().size
        }
    }
}

@Composable
fun CurrentBcnTreesApp() {
    val viewModel = viewModel { TreesViewModel() }
    val tree = viewModel.tree
    val trees = viewModel.treesSize
    Column {
        Text("Hi ha $trees arbres")
    }
}