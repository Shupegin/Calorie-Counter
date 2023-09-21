package cal.calor.caloriecounter.AddNewFoodScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.toLowerCase


@Composable
fun AddFoodScreen(viewModel: AddFoodScreenViewModel) {
    var  category by remember { mutableStateOf("") }
    var  name by remember { mutableStateOf("") }
    var  calories by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
           Text(text = "Введите данные")
            OutlinedTextField(
                value = category,
                onValueChange = {it.let {
                    category = it
                }},
                label = {
                    Text(
                        text = "Название категории",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Red
                )
            )
            OutlinedTextField(
                value = name,
                onValueChange = {it.let {
                    name = it
                }},
                label = {
                    Text(
                        text = "Название блюда",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Red
                )
            )

            OutlinedTextField(
                value = calories,
                onValueChange = {it.let {
                    calories = it
                }},
                label = {
                    Text(
                        text = "калории на 100грамм",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Red
                )
            )
            Button(onClick = {
                val dish = Dish(category = category.toLowerCase(), name = name.toLowerCase(), calories = calories.toIntOrNull() ?: 0)
                viewModel.addDatabase(dish = dish)
            }) {
                Text(text = "Добавить в базу")

            }
        }
    }
}