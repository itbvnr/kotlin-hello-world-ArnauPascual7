package cat.itb.m78.exercices.part2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
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
data class Country(
    val abbreviation: String,
    val capital: String,
    val currency: String,
    val name: String,
    val phone: String,
    val media: Media,
    val id: Int
)

@Serializable
data class Media(val flag: String, val emblem: String, val orthographic: String)

class CountriesViewModel : ViewModel() {
    var countries by mutableStateOf<List<Country>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            countries = CountriesApi.countries()
        }
    }
}

object CountriesApi {
    val url = "https://api.sampleapis.com/countries/countries"
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun countries() = client.get(url).body<List<Country>>()
}

@Composable
fun CountriesApp() {
    val viewModel = viewModel { CountriesViewModel() }
    val countries = viewModel.countries
    Column(Modifier.fillMaxSize()) {
        if (countries != null) {
            LazyColumn {
                countries.forEach { country ->
                    item {
                        Row {
                            Text("País: ${country.name} - Capital: ${country.capital}")
                            Spacer(Modifier.width(10.dp))
                            AsyncImage(
                                model = country.media.flag,
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
                }
            }
        }
        else {
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }
    }
}