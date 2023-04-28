package com.example.caloriecounter.HistoryScreen

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.caloriecounter.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropMenu(viewModel: MainViewModel){
    val options = listOf("День", "Неделя", "Две недели","Месяц")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    val foodList = viewModel.foodListDAO.observeAsState(listOf())

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("История") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        viewModel.sendSelectedOptionText(selectedOptionText, listFood = foodList.value)
                        expanded = false
                    }
                ){
                    Text(text = selectionOption)
                }
            }
        }
    }
}
