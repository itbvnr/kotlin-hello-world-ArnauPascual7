package cat.itb.m78.exercices.part1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.*

class HelloByeViewModel : ViewModel() {
    var score = mutableStateOf(0)
    var score2 = mutableStateOf(0)
    fun addScore(){
        score.value += 1
    }
    fun addScore2(){
        score2.value += 1
    }
}

@Composable
fun CounterApp() {
    val viewModel = viewModel { HelloByeViewModel() }
    Row {
        Column {
            Text(viewModel.score.value.toString())
            Button(onClick = { viewModel.addScore() }) {
                Text("Score")
            }
        }
        Column {
            Text(viewModel.score2.value.toString())
            Button(onClick = { viewModel.addScore2() }) {
                Text("Score")
            }
        }
    }
}