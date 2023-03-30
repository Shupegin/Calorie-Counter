package com.example.caloriecounter.dialog

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.caloriecounter.MainViewModel

@Composable
fun dialog(dialogState: MutableState<Boolean>, viewModel: MainViewModel){
    var username by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = {
                dialogState.value = false
            },
            title = { Text(text = "Дата: ") },

            text = {OutlinedTextField(
                value = username,
                onValueChange = { username =  it },
                label = { Text("Что ел?") },
            )},

            dismissButton = {
                Button(onClick = {
                    dialogState.value = false},

                    ) {
                    Text(text = "cancel", color = Color.Black)
                }
            },

            confirmButton = {
                Button(onClick = {
                    val foodModel = FoodModel("20.03.2023", username)

                    viewModel.addInfoFoodBtn(foodModel)
                    dialogState.value = false},

                    ) {
                    Text(text = "Ok", color = Color.Black)
                }
            }

        )
}
