package com.example.caloriecounter

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import com.example.caloriecounter.dialog.FoodModel


@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onItem: () -> Unit,
    owner: LifecycleOwner,

){
    val selectedNavItem by viewModel.selectedNavItem.observeAsState(NavigationItem.Home)
    Scaffold(bottomBar ={
        BottomNavigation {

            val item = listOf(
                NavigationItem.Home,
                NavigationItem.Favourite,
                NavigationItem.Profile,
            )
            item.forEach{ item ->
                BottomNavigationItem(
                    selected = selectedNavItem == item,
                    onClick = {viewModel.selectNavItem(item)},
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
        when(selectedNavItem){
            NavigationItem.Home ->{
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                    onItem = onItem,
                    owner = owner
                )
            }
            NavigationItem.Favourite -> Text(text = "Favourite", color = Color.Black)
            NavigationItem.Profile -> Text(text = "Profile", color = Color.Black)
        }
    }
}