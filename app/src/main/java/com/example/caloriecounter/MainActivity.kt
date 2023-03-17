package com.example.caloriecounter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Observer
import com.example.caloriecounter.dialog.dialog
import com.example.caloriecounter.ui.theme.CalorieCounterTheme


class MainActivity : ComponentActivity() {


    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieCounterTheme {
                val dialogState = remember {
                    mutableStateOf(false)
                }
                if (dialogState.value){
                    dialog(dialogState)
                }

                MainScreen(viewModel, onItem = { dialogState.value = true} )

            }
        }
    }

}




