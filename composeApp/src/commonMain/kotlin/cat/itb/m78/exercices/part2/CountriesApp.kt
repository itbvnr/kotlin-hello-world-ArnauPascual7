package cat.itb.m78.exercices.part2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
data class Coutry(
    val abbreviation: String,
    val capital: String,
    val currency: String,
    val name: String,
    val phone: String,
    val population: Int?,
    val media: Media,
    val id: Int
)

@Serializable
data class Media(val flag: String, val emblem: String, val orthographic: String)

object CountriesApi {
    private val url = "https://api.sampleapis.com/countries/countries"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun countries() = client.get(url).body<List<Coutry>>()
}

class CountriesViewModel : ViewModel() {
    var countries by mutableStateOf<List<Coutry>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            countries = CountriesApi.countries()
        }
    }
}

@Composable
fun CountriesApp() {
    val viewModel = viewModel { CountriesViewModel() }
    val countries = viewModel.countries
    Column {
        countries?.forEach { country ->
            Row {
                Text(country.name)
                Text(country.capital)
                AsyncImage(
                    model = country.media.flag,
                    contentDescription = null,
                )
            }
        }
    }
}