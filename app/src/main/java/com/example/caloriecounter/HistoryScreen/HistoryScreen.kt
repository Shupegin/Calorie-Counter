package com.example.caloriecounter.HistoryScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.caloriecounter.MainViewModel


@Composable
fun HistoryScreen(viewModel: MainViewModel,
                  paddingValues: PaddingValues
){

    Box(modifier = Modifier
        .background(color = Color.Cyan)
        .fillMaxWidth()
        .fillMaxHeight(),
        contentAlignment = Alignment.Center
        )
    {
            LinearProgressIndicator(
                modifier = Modifier
                    .size(200.dp, 100.dp)
                    .graphicsLayer {
                        rotationZ = 90f
                        transformOrigin = TransformOrigin(0f, 0f)
                    },
                backgroundColor = Color.Black,
                color = Color.Yellow,
        )
    }
}









