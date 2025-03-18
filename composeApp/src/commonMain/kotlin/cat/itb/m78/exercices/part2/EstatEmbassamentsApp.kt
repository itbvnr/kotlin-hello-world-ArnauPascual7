package cat.itb.m78.exercices.part2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Shapes
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
import io.ktor.util.*
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
    EstatNavigation()
}

@Composable
fun EstatScreen1(navScreen2: (List<String>)-> Unit) {
    val viewModel = viewModel { EstatViewModel() }
    val estats = viewModel.estats
    val settings: Settings = Settings()
    var favEstat: String? = settings.getStringOrNull("key")
    Column(Modifier.fillMaxSize().padding(25.dp)) {
        if (estats != null) {
            Text("Estat Preferit: $favEstat", fontSize = 20.sp)
            Spacer(Modifier.height(20.dp))
            LazyColumn {
                estats.forEach { estat ->
                    item {
                        Row {
                            Button(onClick = {
                                settings["key"] = estat.estaci
                                navScreen2(listOf(
                                    estat.dia,
                                    estat.estaci,
                                    estat.nivell_absolut,
                                    estat.percentatge_volum_embassat,
                                    estat.volum_embassat
                            )) },
                                shape = RectangleShape
                                ) {
                                Text(estat.estaci)
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
fun EstatScreen2(strings: List<String>, navScreen1: ()-> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(Modifier.width(300.dp), horizontalAlignment = Alignment.Start) {
            Text("Dia: ${strings[0]}")
            Text("Estaci: ${strings[1]}")
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
    data object EstatScreen1
    @Serializable
    data class EstatScreen2(val strings: List<String>)
}

@Composable
fun EstatNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.EstatScreen1) {
        composable<Destination.EstatScreen1> {
            EstatScreen1 { navController.navigate(Destination.EstatScreen2(it)) }
        }
        composable<Destination.EstatScreen2> { backStack ->
            val strings = backStack.toRoute<Destination.EstatScreen2>().strings
            EstatScreen2(
                strings,
                navScreen1 = { navController.navigate(Destination.EstatScreen1) }
            )
        }
    }
}