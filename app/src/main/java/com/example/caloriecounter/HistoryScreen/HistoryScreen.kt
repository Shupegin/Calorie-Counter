package com.example.caloriecounter.HistoryScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import com.example.caloriecounter.MainViewModel
import com.example.caloriecounter.ui.theme.Green700
@Composable
fun HistoryScreen(viewModel: MainViewModel,
                  paddingValues: PaddingValues,
                  owner: LifecycleOwner){


    var dbUid = viewModel.userListDAO.observeAsState(listOf())
    viewModel.loadFirebaseData(dbUid.value)

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        DropMenu(viewModel = viewModel)}
        VerticalProgressBar(viewModel = viewModel, owner = owner)
    }
}











