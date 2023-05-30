package com.example.caloriecounter.RegistrationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController){
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .background(color = Color.Magenta)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    )

    {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally){

            Text(text = "Регистрация")
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = {
                    Text(
                        text = "Ввдеите имя ",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Green
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {
                    Text(
                        text = "Введите пароль",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Green
                )
            )

            OutlinedTextField(
                value = repeatPassword,
                onValueChange = {repeatPassword = it},
                label = {
                    Text(
                        text = "повторите пароль",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Green
                )
            )
            OutlinedTextField(
                value = calories,
                onValueChange = {calories = it},
                label = {
                    Text(
                        text = "Введите ориентрировочное колличество каллорий в день",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Green
                )
            )
        }

    }
}