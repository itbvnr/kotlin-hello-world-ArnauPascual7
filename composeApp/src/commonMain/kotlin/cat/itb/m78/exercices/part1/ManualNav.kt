package cat.itb.m78.exercices.part1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ManualNav() {
    val viewModel = viewModel { ManualNavViewModel() }
    val currentScreen = viewModel.currentScreen.value
    when (currentScreen) {
        Screen.Screen1 -> CounterApp2(
            navScreen2 = { viewModel.navTo(Screen.Screen2) }
        )
        is Screen.Screen2 -> CounterScreen2(
            navScreen1 = { viewModel.navTo(Screen.Screen1) }
        )
    }
}

private sealed interface Screen {
    data object Screen1 : Screen
    data object Screen2 : Screen
}

private class ManualNavViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.Screen1)
    fun navTo(screen: Screen) { currentScreen.value = screen }
}