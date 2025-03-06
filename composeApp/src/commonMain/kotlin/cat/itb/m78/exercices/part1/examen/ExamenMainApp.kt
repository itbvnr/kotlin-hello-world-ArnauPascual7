package cat.itb.m78.exercices.part1.examen

import kotlinx.serialization.Serializable
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.navigation.*

object Destination {
    @Serializable
    data object MainScreen
    @Serializable
    data class SecondScreen(val price: Float)
}

@Composable
fun ExamenNavScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.MainScreen) {
        composable<Destination.MainScreen> {
            MainScreen { navController.navigate(Destination.SecondScreen(it)) }
        }
        composable<Destination.SecondScreen> { backStack ->
            val price = backStack.toRoute<Destination.SecondScreen>().price
            SecondScreen(price)
        }
    }
}