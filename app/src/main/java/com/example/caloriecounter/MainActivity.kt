package com.example.caloriecounter


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.lifecycleScope
import com.example.caloriecounter.dialog.dialog
import com.example.caloriecounter.ui.theme.CalorieCounterTheme


class MainActivity : ComponentActivity() {


    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {

//            viewModel.foodListDAO.observe(this, Observer {
//                Log.d("RRRRR", "DAO =   $it")
//            })

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




