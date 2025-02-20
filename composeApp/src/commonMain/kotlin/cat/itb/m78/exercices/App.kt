package cat.itb.m78.exercices


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    Box {
        //Text("Your app goes here!", Modifier.align(Alignment.Center), color = Color.Blue, fontSize = 50.sp)
        //HelloWorldApp()
        //WelcomeApp()
        //ResourceApp()
        //ContactApp()
        //MessagesListApp()
        //GoodMorningApp()
        //SayHelloScreenApp()
        //SecretNumberApp()
        //DiceRollerApp()
        //CounterApp()
        CounterNavScreen()
        //ManualNav()
        /*Column(modifier = Modifier.background(Color.Cyan).fillMaxWidth()) {
            Row() {
                Text("Hello")
                Spacer(Modifier.width(100.dp))
                Text("World")
            }
            Row(modifier = Modifier.background(Color.Green).fillMaxWidth()) {
                Spacer(Modifier.height(100.dp))
            }
        }*/
    }
}