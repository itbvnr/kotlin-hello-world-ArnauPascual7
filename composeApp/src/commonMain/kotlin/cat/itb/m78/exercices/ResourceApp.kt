package cat.itb.m78.exercices

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import m78exercices.composeapp.generated.resources.*
import org.jetbrains.compose.resources.*

@Composable
fun ResourceApp() {
    Column {
        Text("This is a resource string")
        Row {
            Spacer(Modifier.width(50.dp))
            Image(
                painter = painterResource(Res.drawable.generatedFace),
                contentDescription = null,
                modifier = Modifier.height(100.dp)
            )
        }
    }
}