package com.example.caloriecounter.LoginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun LoginScreen(navController: NavController){
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .background(color = Color.Red)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column() {
            OutlinedTextField(
                value = login,
                onValueChange = {login = it},
                label = {
                    Text(
                        text = "Введите логин",
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

            Row {
                Text(text = "Восстановление пароля")
                Spacer(Modifier.padding(10.dp))
                Text(text = "Регистрация", modifier = Modifier.clickable(onClick = {
                    navController.navigate("register_page"){
                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                }))

            }
        }
    }
}