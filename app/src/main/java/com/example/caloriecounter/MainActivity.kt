package com.example.caloriecounter


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.caloriecounter.dialog.FoodModel
import com.example.caloriecounter.dialog.dialog
import com.example.caloriecounter.ui.theme.CalorieCounterTheme


class MainActivity : ComponentActivity() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

            CalorieCounterTheme {
                val dialogState = remember {
                    mutableStateOf(false)
                }
                if (dialogState.value){
                    dialog(dialogState, viewModel, lifecycleScope = lifecycleScope)
                }

                MainScreen(viewModel = viewModel, onItem = { dialogState.value = true}, this)

            }
        }

    }

}




