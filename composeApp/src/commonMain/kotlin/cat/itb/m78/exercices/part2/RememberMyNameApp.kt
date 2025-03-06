package cat.itb.m78.exercices.part2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

@Composable
fun RememberMyNameApp() {
    val settings: Settings = Settings()
    var name: String? = settings.getStringOrNull("key")
    if (name == null || name == "") {
        name = "Anonymous"
    }
    var textInput: String by remember { mutableStateOf(name) }
    var textLabel: String by remember { mutableStateOf(name) }
    Column {
        Text("Hola $textLabel")
        TextField(textInput,
            label = { Text("Nom") },
            onValueChange = {
                textInput = it
            }
        )
        Button(onClick = {
            settings["key"] = textInput
            textLabel = textInput
        }) {
            Text("Save")
        }
    }
}