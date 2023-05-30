package com.example.caloriecounter


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caloriecounter.LoginScreen.LoginScreen
import com.example.caloriecounter.RegistrationScreen.RegistrationScreen
import com.example.caloriecounter.dialog.dialog
import com.example.caloriecounter.ui.theme.CalorieCounterTheme
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            CalorieCounterTheme {
                if (true){
                   LoginApplication()
                }else{
                    val dialogState = remember {
                        mutableStateOf(false)
                    }
                    if (dialogState.value){
                        dialog(dialogState, viewModel, lifecycleScope = lifecycleScope)
                    }
                    MainScreen(mainViewModel = viewModel, onItem = { dialogState.value = true}, owner = this)
                }
            }
        }
    }
}

@Composable
fun LoginApplication(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_page", builder ={
        composable("login_page", content = { LoginScreen(navController = navController)})
        composable("register_page", content = { RegistrationScreen(navController = navController)})
    } )
}




