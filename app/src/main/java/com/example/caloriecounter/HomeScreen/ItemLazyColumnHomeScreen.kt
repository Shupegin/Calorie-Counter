package com.example.caloriecounter

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.caloriecounter.dialog.FoodModel



@Composable
fun cardFood(foodModel: FoodModel){
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(text = "Дата: ")
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                ){
                    Text("прием  1 :")
                    Text(text = " ${foodModel.food}", modifier = Modifier.padding(start = 10.dp))
                    Text(text = "Калории = ${foodModel.calories}", modifier = Modifier.padding(start = 10.dp))

                }
                Box(modifier = Modifier.fillMaxWidth()
                    .padding(4.dp),
                    contentAlignment = Alignment.BottomEnd,

                    ) {
                    Text(text = "Сумма калорий = 1000" )
                }

            }
        }
}