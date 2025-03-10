package cat.itb.m78.exercices


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import cat.itb.m78.exercices.part2.*
import cat.itb.m78.exercices.part1.*
import cat.itb.m78.exercices.part1.examen.*
import cat.itb.m78.exercices.theme.AppTheme

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
        //CounterNavScreen()
        //ManualNav()
        //ExamenNavScreen()
        //JokesScreen()
        //CurrentBcnTreesApp()
        //CounterViewApp()
        //RememberMyNameApp()
        CountriesApp()
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