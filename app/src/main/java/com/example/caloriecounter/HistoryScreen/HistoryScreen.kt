package com.example.caloriecounter.HistoryScreen

import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.example.caloriecounter.MainViewModel



@Composable
fun HistoryScreen(viewModel: MainViewModel,
                  paddingValues: PaddingValues
){
    Row(modifier = Modifier
        .fillMaxWidth(),

        horizontalArrangement = Arrangement.Center

    ) {
        DropMenu()
    }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
            VerticalProgressBar()
    }
}











