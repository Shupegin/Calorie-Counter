package com.example.caloriecounter


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

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
                   LoginApplication(viewModelLogin,viewModelRegistration, mainViewModel, this, this)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var intentResult : IntentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)
        if(intentResult != null){
            var content = intentResult.contents
            if (content != null){
                mainViewModel.userId(intentResult.contents.toString())
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)

        }
    }
}

@Composable
fun LoginApplication(viewModel: LoginViewModel,
                     viewModelRegistration: RegistrationViewModel,
                     mainViewModel : MainViewModel,
                     owner: LifecycleOwner,
                     context: Context){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_page", builder ={
        composable("login_page", content = { LoginScreen(navController = navController,viewModel= viewModel, owner = owner)})
        composable("register_page", content = { RegistrationScreen(navController = navController, viewModel= viewModelRegistration)})
        composable("activity_main", content = { MainScreen(mainViewModel = mainViewModel, owner = owner, context = context) })
    })
}




