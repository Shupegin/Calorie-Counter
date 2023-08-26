package cal.calor.caloriecounter.LoginScreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

import androidx.navigation.NavController


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel,owner: LifecycleOwner, context : Context){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val error = viewModel.error.observeAsState("")
    val error_e = viewModel.error_e.observeAsState()
    viewModel.user.observe(owner, androidx.lifecycle.Observer {
        navController.navigate("activity_main") {
                popUpTo(0)
        }
    })


    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column() {
            OutlinedTextField(
                value = email,
                onValueChange = {it.let {
                    email = it
                }},
                label = {
                    Text(
                        text = "Введите email",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Green
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = {it.let {
                    password = it
                }},
                label = {
                    Text(
                        text = "Введите пароль",
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Green
                )
            )
            Button(onClick = {

                if (error_e.value != true){
                    viewModel.login(email = email, password =  password)
                }else{
                    Toast.makeText(context,"Введите пароль или email",Toast.LENGTH_SHORT).show()
                }


            }) {
                Text(text = "Войти")
            }

            Row {
                Text(text = "Восстановление пароля")
                Spacer(Modifier.padding(10.dp))
                Text(text = "Регистрация", modifier = Modifier.clickable(onClick = {
                    navController.navigate("register_page"){
                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                }))

            }
        }
    }
}