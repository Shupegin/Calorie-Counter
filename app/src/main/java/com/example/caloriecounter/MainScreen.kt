package com.example.caloriecounter

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    Scaffold(bottomBar ={
        BottomNavigation {
            val selectedItemPosition = remember {
                mutableStateOf(0)
            }
            val item = listOf(
                NavigationItem.Home,
                NavigationItem.Favourite,
                NavigationItem.Profile,
            )
            item.forEachIndexed{index, item ->
                BottomNavigationItem(
                    selected = selectedItemPosition.value == index,
                    onClick = {selectedItemPosition.value = index},
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
    }) {
        
    }
}