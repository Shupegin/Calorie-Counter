package com.example.caloriecounter.LoginScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val error = viewModel.error.observeAsState("")
    val user = viewModel.user.observeAsState("")

        Log.d("TOASTER", "error = ${error.value}" )
        Log.d("TOASTER", "user = ${user.value}" )


    Box(modifier = Modifier
        .background(color = Color.Red)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column() {
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
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
            Button(onClick = {

                viewModel.login(email = email, password =  password)

            }) {
                Text(text = "Войти")

            }

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