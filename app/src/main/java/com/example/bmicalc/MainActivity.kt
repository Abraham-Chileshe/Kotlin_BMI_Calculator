package com.example.bmicalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.bmicalc.ui.theme.BmicalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmicalcTheme {
                BMICalculatorApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculatorApp() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }
    var resultColor by remember { mutableStateOf(Color.Black) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "123 Start Up - BMI Calculator",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White // Text color
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF6200EE) // Background color
                ),
                modifier = Modifier.wrapContentHeight(),
            )
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height (m)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Gender (M/F)") },
                    keyboardOptions = KeyboardOptions.Default,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (weight.isNotEmpty() && height.isNotEmpty()) {
                            val bmiCalculator = BMICalculator(
                                weight = weight.toFloat(),
                                height = height.toFloat(),
                                gender = gender
                            )
                            val (bmi, message, color) = bmiCalculator.calculateBMI()
                            resultMessage = "BMI: %.2f\n$message".format(bmi)
                            resultColor = color
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Calculate BMI")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = resultMessage,
                    color = resultColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(resultColor.copy(alpha = 0.1f))
                        .padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}
