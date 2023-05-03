package com.example.caloriecounter

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun test (){
    val textState = remember {
         mutableStateOf("")
    }
    OutlinedTextField(
        value = textState.value,
        onValueChange = {textState.value = it},

        label = {
            Text(
                text = "что ел?",
                style = TextStyle(
                    color = Color.Black,
                )
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Green,
            unfocusedBorderColor = Yellow)
    )

}