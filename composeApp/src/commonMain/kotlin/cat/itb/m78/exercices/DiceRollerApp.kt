package cat.itb.m78.exercices

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*
import m78exercices.composeapp.generated.resources.*
import org.jetbrains.compose.resources.*
import kotlin.random.*

@Composable
fun DiceRollerApp() {
    var leftDice by remember { mutableStateOf(0) }
    var rightDice by remember { mutableStateOf(0) }
    val dices = listOf(Res.drawable.empty_dice, Res.drawable.dice_1, Res.drawable.dice_2, Res.drawable.dice_3, Res.drawable.dice_4, Res.drawable.dice_5, Res.drawable.dice_6)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Box {
        Scaffold (
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ){ padding ->
            Image(painter = painterResource(Res.drawable.tapestry),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(Res.drawable.title),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Button(onClick = {
                    leftDice = Random.nextInt(1, 7)
                    rightDice = Random.nextInt(1, 7)

                    if (leftDice == 6 && rightDice == 6) {
                        scope.launch {
                            snackbarHostState.showSnackbar("JACKPOT!",
                                withDismissAction = true)
                        }
                    }
                },
                    modifier = Modifier.width(500.dp)){
                    Text("Roll the dice")
                }
                Row {
                    Image(painter = painterResource(dices[leftDice]),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                    Image(painter = painterResource(dices[rightDice]),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }
    }
}