package cat.itb.m78.exercices.part2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
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
data class Embassament(
    val dia: String,
    val estaci: String,
    val nivell_absolut: String,
    val percentatge_volum_embassat: String,
    val volum_embassat: String
)

class EmbassamentsViewModel : ViewModel() {
    var embassaments by mutableStateOf<List<Embassament>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            embassaments = EmbassamentsApi.listEmbassaments()
        }
    }
}

object EmbassamentsApi {
    val url = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json"
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun listEmbassaments() = client.get(url).body<List<Embassament>>()
}

@Composable
fun EstatEmbassamentsApp() {
    EmbassamentsNavigation()
}

@Composable
fun EmbassamentsScreen1(navScreen2: (List<String>)-> Unit) {
    val viewModel = viewModel { EmbassamentsViewModel() }
    val embassaments = viewModel.embassaments
    val settings = Settings()
    val favEmbassaments = settings.getStringOrNull("key")
    Column(Modifier.fillMaxSize().padding(25.dp)) {
        if (embassaments != null) {
            Text("Embassament Preferit: $favEmbassaments", fontSize = 20.sp)
            Spacer(Modifier.height(20.dp))
            LazyColumn {
                embassaments.forEach { item ->
                    item {
                        Row {
                            Button(onClick = {
                                settings["key"] = item.estaci
                                navScreen2(listOf(
                                    item.dia,
                                    item.estaci,
                                    item.nivell_absolut,
                                    item.percentatge_volum_embassat,
                                    item.volum_embassat
                            )) },
                                shape = RectangleShape
                                ) {
                                Text(item.estaci)
                            }
                        }
                    }
                }
            }
        } else {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun EmbassamentsScreen2(strings: List<String>, navScreen1: ()-> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(strings[1], fontSize = 20.sp)
        Spacer(Modifier.height(25.dp))
        Column(Modifier.width(300.dp), horizontalAlignment = Alignment.Start) {
            Text("Dia: ${strings[0]}")
            Text("Nivell Absolut: ${strings[2]}")
            Text("Percentatge de Volum Embassat: ${strings[3]}")
            Text("Volum Embassat: ${strings[4]}")
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navScreen1() }) {
            Text("Tornar")
        }
    }
}

object Destination {
    @Serializable
    data object EmbassamentsScreen1
    @Serializable
    data class EmbassamentsScreen2(val strings: List<String>)
}

@Composable
fun EmbassamentsNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.EmbassamentsScreen1) {
        composable<Destination.EmbassamentsScreen1> {
            EmbassamentsScreen1 { navController.navigate(Destination.EmbassamentsScreen2(it)) }
        }
        composable<Destination.EmbassamentsScreen2> { backStack ->
            val strings = backStack.toRoute<Destination.EmbassamentsScreen2>().strings
            EmbassamentsScreen2(
                strings,
                navScreen1 = { navController.navigate(Destination.EmbassamentsScreen1) }
            )
        }
    }
}