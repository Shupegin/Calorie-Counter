package com.example.caloriecounter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.caloriecounter.dialog.FoodModel
import com.example.caloriecounter.dialog.dialog


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onItem: () -> Unit,
    owner: LifecycleOwner,
    paddingValues: PaddingValues
){


    Box(Modifier
        .background(color = Color.Gray)
    ){
        var foodModel = ArrayList<FoodModel>()
        viewModel.addInfoFood.observe(owner, Observer {
            foodModel = it
        })

      LazyColumn(modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 55.dp)
      ){
          items(items = foodModel){foodModel->
              cardFood(foodModel)
          }
      }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
            contentAlignment = Alignment.BottomCenter

        ){
            FloatingActionButton(onClick = {
                onItem.invoke()
            }) {
                Icon(imageVector = Icons.Default.Add,contentDescription = null)
            }

        }
    }

}

