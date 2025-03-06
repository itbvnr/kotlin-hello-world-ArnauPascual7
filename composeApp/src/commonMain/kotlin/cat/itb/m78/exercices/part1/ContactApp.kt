package cat.itb.m78.exercices.part1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import m78exercices.composeapp.generated.resources.*
import org.jetbrains.compose.resources.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*

data class Contact(val fullName: String, val email: String, val phone: String)
val contact = Contact("Marta Casserres", "marta@example.com", "934578484")

@Composable
fun ContactApp() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(Res.drawable.generatedFace),
            contentDescription = null,
            modifier = Modifier.size(100.dp).clip(CircleShape)
        )
        Text(contact.fullName, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Column(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color.LightGray).padding(15.dp)) {
            Row {
                Icon(Icons.Default.Email, "email", Modifier.size(20.dp))
                Spacer(Modifier.width(5.dp))
                Text(contact.email)
            }
            Spacer(Modifier.height(10.dp))
            Row {
                Icon(Icons.Default.Phone, "phone", Modifier.size(20.dp))
                Spacer(Modifier.width(5.dp))
                Text(contact.phone)
            }
        }
    }
}