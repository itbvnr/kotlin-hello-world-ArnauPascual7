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
    var leftDice by remember { mutableStateOf(Res.drawable.empty_dice) }
    var rightDice by remember { mutableStateOf(Res.drawable.empty_dice) }
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
                    val leftRandom = Random.nextInt(1, 7)
                    if (leftRandom == 1) { leftDice = Res.drawable.dice_1 }
                    else if (leftRandom == 2) { leftDice = Res.drawable.dice_2 }
                    else if (leftRandom == 3) { leftDice = Res.drawable.dice_3 }
                    else if (leftRandom == 4) { leftDice = Res.drawable.dice_4 }
                    else if (leftRandom == 5) { leftDice = Res.drawable.dice_5 }
                    else if (leftRandom == 6) { leftDice = Res.drawable.dice_6 }

                    val rightRandom = Random.nextInt(1, 7)
                    if (rightRandom == 1) { rightDice = Res.drawable.dice_1 }
                    else if (rightRandom == 2) { rightDice = Res.drawable.dice_2 }
                    else if (rightRandom == 3) { rightDice = Res.drawable.dice_3 }
                    else if (rightRandom == 4) { rightDice = Res.drawable.dice_4 }
                    else if (rightRandom == 5) { rightDice = Res.drawable.dice_5 }
                    else if (rightRandom == 6) { rightDice = Res.drawable.dice_6 }

                    if (leftRandom == 6 && rightRandom == 6) {
                        scope.launch {
                            snackbarHostState.showSnackbar("JACKPOT!")
                        }
                    }
                },
                    modifier = Modifier.width(500.dp)){
                    Text("Roll the dice")
                }
                Row {
                    Image(painter = painterResource(leftDice),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                    Image(painter = painterResource(rightDice),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }
    }
}