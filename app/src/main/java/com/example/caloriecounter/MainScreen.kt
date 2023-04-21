package com.example.caloriecounter


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.caloriecounter.navigation.AppNavGraph
import com.example.caloriecounter.navigation.NavigationItem


@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onItem: () -> Unit,
    owner: LifecycleOwner,

){
    val navHostController = rememberNavController()
    Scaffold(bottomBar ={
        BottomNavigation {
            val navBackStackEntry  by navHostController.currentBackStackEntryAsState()
            val currentRout = navBackStackEntry?.destination?.route
            val item = listOf(
                NavigationItem.Home,
                NavigationItem.Favourite,
                NavigationItem.Profile,
            )
            item.forEach{ item ->
                BottomNavigationItem(
                    selected = currentRout == item.screen.route,
                    onClick = {navHostController.navigate(item.screen.route)},
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
    },) {paddingValues ->
        AppNavGraph(
            navHostController = navHostController ,
            homeScreenContent = { HomeScreen(viewModel = viewModel, paddingValues = paddingValues, onItem = onItem)},
            historyScreenContent = {Text(text = "Favourite", color = Color.Black)},
            profileScreenContent = {Text(text = "Profile", color = Color.Black)}
        )

    }
}