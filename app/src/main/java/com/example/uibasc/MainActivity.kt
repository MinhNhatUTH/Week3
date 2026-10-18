package com.example.uibasc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppChinh()
        }
    }
}

@Composable
fun AppChinh() {
    val dieuhuong = rememberNavController()

    NavHost(navController = dieuhuong, startDestination = "manhinh1") {
        composable("manhinh1") { ManhinhDau(dieuhuong) }
        composable("manhinh2") { ManhinhList(dieuhuong) }
        // {tieude} là phần tên mục được gửi sang (Text, TextField, ...)
        composable("manhinh3/{tieude}") { duLieu ->
            val tieude = duLieu.arguments?.getString("tieude") ?: ""
            Manhinhchitiet(dieuhuong, tieude)
        }
    }
}

@Composable
fun ManhinhDau(dieuhuong: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.Green, RoundedCornerShape(24.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Jetpack Compose", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { dieuhuong.navigate("manhinh2") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "I'm ready")
        }
    }
}

// Một ô trong danh sách
@Composable
fun OList(ten: String, moTa: String, khiBam: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.Cyan.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
            .clickable { khiBam() }
            .padding(12.dp)
    ) {
        Text(text = ten, fontWeight = FontWeight.Bold)
        Text(text = moTa)
    }
}

@Composable
fun ManhinhList(dieuhuong: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "UI Components List",
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Display", fontWeight = FontWeight.Bold)
        OList("Text", "Displays text") { dieuhuong.navigate("manhinh3/Text") }
        OList("Image", "Displays an image") { dieuhuong.navigate("manhinh3/Image") }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Input", fontWeight = FontWeight.Bold)
        OList("TextField", "Input field for text") { dieuhuong.navigate("manhinh3/TextField") }
        OList("PasswordField", "Input field for passwords") { dieuhuong.navigate("manhinh3/PasswordField") }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Layout", fontWeight = FontWeight.Bold)
        OList("Column", "Arranges elements vertically") { dieuhuong.navigate("manhinh3/Column") }
        OList("Row", "Arranges elements horizontally") { dieuhuong.navigate("manhinh3/Row") }
    }
}

@Composable
fun Manhinhchitiet(dieuhuong: NavController, tieude: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "<",
                color = Color.Blue,
                fontSize = 24.sp,
                modifier = Modifier.clickable { dieuhuong.popBackStack() }
            )
            Text(
                text = "$tieude Detail",
                color = Color.Blue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (tieude == "Text") {
            Text(
                text = buildAnnotatedString {
                    append("The ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick")
                    }
                    withStyle(SpanStyle(color = Color.Red, fontSize =44.sp)) {
                        append("Brown\n")
                    }
                    append("fox ")
                    withStyle(SpanStyle(letterSpacing = 6.sp)) {
                        append("jumps")
                    }
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append("over\n")
                    }
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the")
                    }
                    withStyle(SpanStyle(fontFamily = FontFamily.Cursive, fontStyle = FontStyle.Italic)) {
                        append("lazy ")
                    }
                    append("dog.")
                },
                fontSize = 30.sp,
                lineHeight = 50.sp,
            )
        } else {
            Text(
                text = "Không Chưa làm",
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}