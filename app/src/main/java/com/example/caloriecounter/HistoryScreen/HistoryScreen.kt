package com.example.caloriecounter.HistoryScreen

import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import com.example.caloriecounter.MainViewModel



@Composable
fun HistoryScreen(viewModel: MainViewModel,
                  paddingValues: PaddingValues,
                  owner: LifecycleOwner
){
    Row(modifier = Modifier
        .fillMaxWidth(),

        horizontalArrangement = Arrangement.Center

    ) {
        DropMenu(viewModel = viewModel)
    }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
            VerticalProgressBar(viewModel = viewModel, owner = owner)
    }
}











