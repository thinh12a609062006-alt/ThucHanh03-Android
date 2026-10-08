package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalulatorScreen()
        }
    }
}

@Composable
fun CalulatorScreen() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center//đặt các thành phần theo chiều dọc theo thứ tự

    ) {
        Text(
            text = "Thực Hành 03",
        )
            Spacer(
                modifier = Modifier.height(60.dp)
        )
        TextField(
            value = number1,
            onValueChange = { number1 = it },
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    val a = number1.toDoubleOrNull()
                    val b = number2.toDoubleOrNull()
                    if (a != null && b != null) {
                        result = (a + b).toString()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Red)

            ) {
                Text("+")
            }

            Button(
                onClick = {
                    val a = number1.toDoubleOrNull()
                    val b = number2.toDoubleOrNull()
                    if (a != null && b != null) {
                        result = (a - b).toString()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Yellow)
            ) {
                Text("-")
            }

            Button(
                onClick = {
                    val a = number1.toDoubleOrNull()
                    val b = number2.toDoubleOrNull()
                    if (a != null && b != null) {
                        result = (a * b).toString()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text("*")
            }

            Button(
                onClick = {
                    val a = number1.toDoubleOrNull()
                    val b = number2.toDoubleOrNull()
                    if (a != null && b != null) {
                        if (b != 0.0) {
                            result = (a / b).toString()
                        } else {
                            result = "Không thể chia cho 0"
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text("/")
            }
        }
        TextField(
            value = number2,
            onValueChange = { number2 = it },
            modifier = Modifier.fillMaxWidth()
        )
            Text(
                text = "Kết quả: $result"
            )
        }
    }

