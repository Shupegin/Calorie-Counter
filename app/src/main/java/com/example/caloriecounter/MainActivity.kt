package com.example.caloriecounter


import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caloriecounter.LoginScreen.LoginScreen
import com.example.caloriecounter.LoginScreen.LoginViewModel
import com.example.caloriecounter.ProfileScreen.ProfileViewModel
import com.example.caloriecounter.RegistrationScreen.RegistrationScreen
import com.example.caloriecounter.RegistrationScreen.RegistrationViewModel
import com.example.caloriecounter.ui.theme.CalorieCounterTheme
class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModelLogin: LoginViewModel
    private lateinit var viewModelRegistration: RegistrationViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModelLogin = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModelRegistration = ViewModelProvider(this)[RegistrationViewModel::class.java]
        setContent {
            CalorieCounterTheme {
                   LoginApplication(viewModelLogin,viewModelRegistration, mainViewModel, this)
            }
        }
    }
}

@Composable
fun LoginApplication(viewModel: LoginViewModel , viewModelRegistration: RegistrationViewModel, mainViewModel : MainViewModel, owner: LifecycleOwner){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_page", builder ={
        composable("login_page", content = { LoginScreen(navController = navController,viewModel= viewModel, owner = owner)})
        composable("register_page", content = { RegistrationScreen(navController = navController, viewModel= viewModelRegistration)})
        composable("activity_main", content = { MainScreen(mainViewModel = mainViewModel, owner = owner) })
    })
}




