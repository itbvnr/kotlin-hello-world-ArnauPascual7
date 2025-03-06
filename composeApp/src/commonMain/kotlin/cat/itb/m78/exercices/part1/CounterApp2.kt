package cat.itb.m78.exercices.part1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.*

class CounterViewModel: ViewModel() {
    var local by mutableStateOf(0)
    var visit by mutableStateOf(0)

    fun addLocalScore() { local++ }
    fun addVisitScore() { visit++ }
}

@Composable
fun CounterApp2(/*viewModel: CounterViewModel, */navScreen2: () -> Unit) {
    val viewModel = viewModel { CounterViewModel() }
    val local = viewModel.local
    val visit = viewModel.visit
    CounterView(local, visit, viewModel::addLocalScore, viewModel::addVisitScore, navScreen2)
    /*MaterialTheme {
        Row {
            Column {
                Text(viewModel.local.toString())
                Button( onClick = { viewModel.updateLocal() }) { Text("Score") }

            }
            Column {
                Text(viewModel.visit.toString())
                Button( onClick = { viewModel.updateVisit() } ) { Text("Score") }
            }
        }
    }*/
}

@Composable
fun CounterView(local: Int, visit: Int, updateLocal: () -> Unit, updateVisit: () -> Unit, navScreen2: () -> Unit) {
    MaterialTheme {
        Row {
            Column {
                Text(local.toString())
                Button( onClick = { updateLocal() }) { Text("Score") }

            }
            Column {
                Text(visit.toString())
                Button( onClick = { updateVisit() } ) { Text("Score") }
            }
            Column {
                Button(onClick = navScreen2) { Text("Continue") }
            }
        }
    }
}
@Composable
fun CounterScreen2(/*viewModel: CounterViewModel,*/ navScreen1: () -> Unit) {
    val viewModel = viewModel { CounterViewModel() }
    val local = viewModel.local
    val visit = viewModel.visit
    Column {
        Text("Local: $local")
        Text("Visit: $visit")
        Button(onClick = navScreen1) { Text("Return") }
    }
}

/*object Destination {
    @Serializable
    data object Screen1
    @Serializable
    data object Screen2
}

@Composable
fun CounterNavScreen() {
    val navController = rememberNavController()
    //val viewModel = viewModel { CounterViewModel() }
    NavHost(navController = navController, startDestination = Destination.Screen1) {
        composable<Destination.Screen1> { CounterApp2/*(viewModel)*/ { navController.navigate(Destination.Screen2) } }
        composable<Destination.Screen2> { CounterScreen2/*(viewModel)*/ { navController.navigate(Destination.Screen1) } }
    }
}*/