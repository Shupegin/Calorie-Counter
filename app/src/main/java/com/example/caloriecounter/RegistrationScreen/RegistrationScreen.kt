package com.example.caloriecounter.RegistrationScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController,viewModel: RegistrationViewModel){
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    val error = viewModel.error.observeAsState("")
    val user = viewModel.user.observeAsState("")

    Log.d("TOASTER", "error 2  = ${error.value}" )
    Log.d("TOASTER", "user 2 = ${user.value}" )

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
                value = email,
                onValueChange = {email = it},
                label = {
                    Text(
                        text = "Введите email ",
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
            Button(onClick = {
                viewModel.singUp(email = email, password = password, calories = calories.toInt())
            }) {
                Text(text = "Регистрация")
            }
        }

    }
}