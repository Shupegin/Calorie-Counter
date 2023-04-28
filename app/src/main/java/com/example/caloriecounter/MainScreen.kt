package com.example.caloriecounter


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.caloriecounter.HistoryScreen.HistoryScreen
import com.example.caloriecounter.HistoryScreen.HistoryViewModel
import com.example.caloriecounter.navigation.*


@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    onItem: () -> Unit,
    owner: LifecycleOwner,

){
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
                    onClick = { navigationState.navigateTo(item.screen.route)  },
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
            navHostController = navigationState.navHostController ,
            homeScreenContent = { HomeScreen(viewModel = mainViewModel, paddingValues = paddingValues, onItem = onItem)},
            historyScreenContent = { HistoryScreen(viewModel = mainViewModel, paddingValues = paddingValues,owner)},
            profileScreenContent = {Text(text = "Profile", color = Color.Black)}
        )

    }
}