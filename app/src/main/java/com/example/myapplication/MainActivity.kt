package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalulatorScreen()
        }
    }
}


fun formatNumber(value: Double): String {
    return if (value % 1.0 == 0.0) value.toLong().toString() else value.toString()
}

@Composable
fun CalulatorScreen() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val invalid1 = number1.isNotEmpty() && number1.toDoubleOrNull() == null
    val invalid2 = number2.isNotEmpty() && number2.toDoubleOrNull() == null

    fun calculate(op: Char) {
        val a = number1.toDoubleOrNull()
        val b = number2.toDoubleOrNull()

        if (a == null || b == null) {
            result = "Không hợp lệ"
            return
        }

        result = when (op) {
            '+' -> formatNumber(a + b)
            '-' -> formatNumber(a - b)
            '*' -> formatNumber(a * b)
            '/' -> if (b != 0.0) formatNumber(a / b) else "Không thể chia cho 0"
            else -> ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Thực hành 03",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(60.dp))

        OutlinedTextField(
            value = number1,
            onValueChange = {
                number1 = it
                result = ""
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            isError = invalid1,
            supportingText = { if (invalid1) Text("Không hợp lệ") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OpButton("+", Color(0xFFE05252)) { calculate('+') }
            OpButton("-", Color(0xFFDDA94B)) { calculate('-') }
            OpButton("*", Color(0xFF5B3DE0)) { calculate('*') }
            OpButton("/", Color(0xFF1C1C1C)) { calculate('/') }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = number2,
            onValueChange = {
                number2 = it
                result = ""
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            isError = invalid2,
            supportingText = { if (invalid2) Text("Không hợp lệ") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Kết quả: $result")
    }
}

@Composable
fun OpButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(64.dp),
        shape = RoundedCornerShape(8.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text, color = Color.White)
    }
}