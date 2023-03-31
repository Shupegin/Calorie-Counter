package com.example.caloriecounter.dialog

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.caloriecounter.MainViewModel

@Composable
fun dialog(dialogState: MutableState<Boolean>, viewModel: MainViewModel){
    var userfood by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = {
                dialogState.value = false
            },
            title = { Text(text = "Дата: ") },

            text = {OutlinedTextField(
                value = userfood,
                onValueChange = { userfood =  it },
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
                    val foodModel = FoodModel(food = userfood)

                    viewModel.addInfoFoodBtn(foodModel)
                    dialogState.value = false},

                    ) {
                    Text(text = "Ok", color = Color.Black)
                }
            }

        )
}
