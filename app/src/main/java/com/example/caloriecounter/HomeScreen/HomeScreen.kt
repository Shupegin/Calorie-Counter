package com.example.caloriecounter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onItem: () -> Unit,
    paddingValues: PaddingValues
){


    Box(Modifier
        .background(color = Color.Gray)
    ){
        val foodList = viewModel.foodListDAO.observeAsState(listOf())

        val list = foodList.value.groupBy { it.dataCurrent }

      LazyColumn(modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 55.dp)){

          list?.forEach{(dataCurrent,listFood)->
              stickyHeader{
                  Box(modifier = Modifier
                      .fillMaxWidth()
                      .padding(1.dp)
                      .background(Color.White),
                      contentAlignment = Alignment.Center
                  ){
                      Text(text = dataCurrent.toString(), style = MaterialTheme.typography.h6)
                  }
              }
              items(listFood){foodModel ->
                  cardFood(foodModel = foodModel)

              }
            item() {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    contentAlignment = Alignment.BottomEnd,
                    ) {
                    var totalCalories = viewModel.getCalories(listFood)
                    Text(text = "Сумма калорий =  $totalCalories" )
                }
            }
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

