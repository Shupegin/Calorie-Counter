package com.example.caloriecounter.dialog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.caloriecounter.MainViewModel
import com.example.caloriecounter.pojo.FoodModel



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dialog(dialogState: MutableState<Boolean>,
           viewModel: MainViewModel,
){
    var userfood by remember { mutableStateOf("") }
    var numberOfGrams by remember { mutableStateOf("") }
    var numberOfCalories by remember { mutableStateOf("") }
        Dialog(
            onDismissRequest = {
                dialogState.value = false
            },

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Card() {
                    Column(
                        modifier = Modifier
                            .background(Color.White),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(text = "Дата: ${viewModel.getCurrentDate()} ")

                        OutlinedTextField(
                            value = userfood,
                            onValueChange = { userfood = it},
                            label = {
                                Text(
                                    text = "что ел?",
                                    style = androidx.compose.ui.text.TextStyle(
                                        color = Color.Black
                                    )
                                )
                            }, colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Green
                            )
                        )

                        OutlinedTextField(
                            value = numberOfGrams,
                            onValueChange = { numberOfGrams = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = {
                                Text(
                                    text = "колличество грамм?",
                                    style = androidx.compose.ui.text.TextStyle(
                                        color = Color.Black
                                    )
                                )
                            }, colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Green
                            )
                        )

                        OutlinedTextField(
                            value = numberOfCalories,
                            onValueChange = { numberOfCalories = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = {
                                Text(
                                    text = "каллории?",
                                    style = androidx.compose.ui.text.TextStyle(
                                        color = Color.Black
                                    )
                                )
                            }, colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Green
                            )
                        )

                        Row() {
                            Button(onClick = { dialogState.value = false}) {
                                Text(text = "Закрыть ")
                            }
                            Spacer(modifier = Modifier.padding(end = 20.dp))
                            Button(onClick = {
                                val foodModel = FoodModel(food = userfood, calories = numberOfCalories.toInt(), gramm = numberOfGrams.toInt())
                                viewModel.addInfoFoodBtn(foodModel)
                                dialogState.value = false
                            }) {
                                Text(text = "Ок")
                            }
                        }
                    }
                }

            }

        }
}
