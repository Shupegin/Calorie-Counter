package com.example.caloriecounter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cal.calor.caloriecounter.pojo.FoodModel



@Composable
fun cardFood(foodModel: FoodModel, ){
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
            shape = RoundedCornerShape(12.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                ){
                    Text("Название: ")
                    Text(text = "${foodModel.food}", modifier = Modifier.padding(start = 10.dp))

                }
                Text(text = "Колличество грамм = ${foodModel.gramm}", modifier = Modifier.padding(start = 5.dp))
                Text(text = "Калории = ${foodModel.calories}", modifier = Modifier.padding(start = 5.dp))
            }
        }
}


