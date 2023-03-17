package com.example.caloriecounter.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun dialog(dialogState: MutableState<Boolean>){

        AlertDialog(
            onDismissRequest = {
                dialogState.value = false
            },
            title = { Text(text = "Dialog") },
            text = { Text(text = "This") },
            confirmButton = {
                Button(onClick = { },

                    ) {
                    Text(text = "Close", color = Color.Black)
                }
            }

        )

}