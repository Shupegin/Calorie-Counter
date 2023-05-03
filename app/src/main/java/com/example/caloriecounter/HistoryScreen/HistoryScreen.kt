package com.example.caloriecounter.HistoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import com.example.caloriecounter.MainViewModel
import com.example.caloriecounter.ui.theme.Green700


@Composable
fun HistoryScreen(viewModel: MainViewModel,
                  paddingValues: PaddingValues,
                  owner: LifecycleOwner
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Green700)
    ){
        Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
        ) {
        DropMenu(viewModel = viewModel)
    }
        VerticalProgressBar(viewModel = viewModel, owner = owner)
    }
}











