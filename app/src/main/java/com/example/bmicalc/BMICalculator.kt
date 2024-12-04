package com.example.bmicalc

import androidx.compose.ui.graphics.Color

enum class BMIResult(val message: String, val color: Color) {
    UNDERWEIGHT("You are underweight.", Color.Red),
    NORMAL("Your weight is normal.", Color.Green),
    OVERWEIGHT("You are overweight.", Color.Red)
}

data class BMICalculator(
    val weight: Float,
    val height: Float,
    val gender: String
) {
    fun calculateBMI(): Triple<Float, String, Color> {
        val bmi = weight / (height * height)
        val result = when {
            bmi < 18.5 -> BMIResult.UNDERWEIGHT
            bmi in 18.5..24.9 -> BMIResult.NORMAL
            else -> BMIResult.OVERWEIGHT
        }
        return Triple(bmi, result.message, result.color)
    }
}
