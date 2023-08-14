package com.example.caloriecounter


import android.content.Context
import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.caloriecounter.HistoryScreen.HistoryScreen
import com.example.caloriecounter.ProfileScreen.ProfileScreen
import com.example.caloriecounter.dialog.dialog
import com.example.caloriecounter.navigation.*


@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    owner: LifecycleOwner,
    context: Context
){
    val dialogState = remember {
        mutableStateOf(false)
    }


    if (dialogState.value){
       dialog(dialogState = dialogState, viewModel = mainViewModel )
    }


    val navigationState = rememberNavigationState()
    Scaffold(bottomBar ={
        BottomNavigation {
            val navBackStackEntry  by navigationState.navHostController.currentBackStackEntryAsState()
            val currentRout = navBackStackEntry?.destination?.route
            val item = listOf(
                NavigationItem.Home,
                NavigationItem.Favourite,
                NavigationItem.Profile,
            )
            item.forEach{ item ->
                BottomNavigationItem(
                    selected = currentRout == item.screen.route,
                    onClick = { navigationState.navigateTo(item.screen.route)
                              },
                    icon = {
                        Icon(item.icon, contentDescription = null )
                    },
                    label = {
                        Text(text = stringResource(id = item.titleResId))
                    },
                    selectedContentColor =  MaterialTheme.colors.onPrimary,
                    unselectedContentColor = MaterialTheme.colors.onSecondary
                )
            }
        }
    },){ paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent =    { HomeScreen(viewModel = mainViewModel, paddingValues = paddingValues, onItem = {dialogState.value = true})},
            historyScreenContent = { HistoryScreen(viewModel = mainViewModel, paddingValues = paddingValues,owner)},
            profileScreenContent = { ProfileScreen(viewModel = mainViewModel, paddingValues = paddingValues,owner,context)})
    }
}

