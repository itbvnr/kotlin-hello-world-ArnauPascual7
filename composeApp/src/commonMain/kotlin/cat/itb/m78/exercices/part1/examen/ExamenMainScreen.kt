package cat.itb.m78.exercices.part1.examen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class ShirtsViewModel: ViewModel() {
    val listCompletShirtSizes = listOf("Petita (10.0€)", "Mitjana (15.0€)", "Gran (20.0€)")
    val listShirtSizes = listOf("Petita", "Mitjana", "Gran")
    var shirts by mutableStateOf(0)
    var shirtSize by mutableStateOf("")
    fun updateShirts(newShirts: Int) { shirts = newShirts }
    fun updateShirtSize(newShirtSize: String) {shirtSize = newShirtSize}
}

@Composable
fun MainScreen(navToSecondScreen: (Float)-> Unit) {
    var price: Float by remember { mutableStateOf(0f) }
    val shirtsViewModel = viewModel { ShirtsViewModel() }
    val shirts = shirtsViewModel.shirts
    val shirtSize = shirtsViewModel.shirtSize
    var text by remember { mutableStateOf("") }
    val shirtSizePrices = listOf(10f, 15f, 20f)
    val (shirtSizePriceSelected, onShirtSizePriceSelected) = remember { mutableStateOf(10f) }
    Column {
        Text("Venda de Samarretes", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        // TextField per al nombre de samarretes
        TextField(text,
            label = { Text("Samarretes") },
            onValueChange = {
                text = it
                if (text != "") {
                    shirtsViewModel.updateShirts(text.toInt())
                } else {
                    shirtsViewModel.updateShirts(0)
                }
            }
        )
        Spacer(Modifier.height(20.dp))
        // Radio Buttons amb les talles
        shirtSizePrices.forEach { radioPrice ->
            val position = shirtSizePrices.indexOf(radioPrice)
            Row(
                Modifier.selectable(
                    selected = (radioPrice == shirtSizePriceSelected),
                    onClick = { onShirtSizePriceSelected(radioPrice) },
                    role = Role.RadioButton
                )
            ) {
                RadioButton(
                    selected = (radioPrice == shirtSizePriceSelected),
                    onClick = null
                )
                Text(shirtsViewModel.listCompletShirtSizes[position])
                Spacer(Modifier.height(10.dp))
            }
            if (shirtSizePriceSelected == shirtSizePrices[position]) {
                shirtsViewModel.updateShirtSize(shirtsViewModel.listShirtSizes[position])
            }
        }
        // Càlcul de preu
        price = if (shirts != 0) {
            shirtSizePriceSelected * shirts
        } else {
            0f
        }
        Spacer(Modifier.height(20.dp))
        // Textos
        Text("Número de samarretes: $shirts")
        Text("Talla Seleccionada: $shirtSize")
        Text("Preu de les samarretes: $price")
        Spacer(Modifier.height(20.dp))
        // Botó per anar a la segona pantalla
        Button(onClick = {
            if (text != "" && text.toInt() != 0) navToSecondScreen(price)
        }) {
            Text("Comanda")
        }
    }
}