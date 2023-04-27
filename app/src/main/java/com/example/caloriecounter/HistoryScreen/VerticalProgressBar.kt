package com.example.caloriecounter.HistoryScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.caloriecounter.ui.theme.Gray500


@Composable
fun VerticalProgressBar() {
    var progressCount: Int by remember { mutableStateOf(0) }
    var progress by remember { mutableStateOf(0f) }

    when (progressCount) {
        0 -> progress = 0.0f
        1 -> progress = 0.1f
        2 -> progress = 0.2f
        3 -> progress = 0.3f
        4 -> progress = 0.4f
        5 -> progress = 0.5f
        6 -> progress = 0.6f
        7 -> progress = 0.7f
        8 -> progress = 0.8f
        9 -> progress = 0.9f
        10 -> progress = 1.0f
    }

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 1000,
            delayMillis = 100,
            easing = LinearOutSlowInEasing
        )
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp, start = 30.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .width(40.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            // for the background of the ProgressBar
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(Gray500)
            )
            // for the progress of the ProgressBar
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .fillMaxHeight(size)
                    .clip(RoundedCornerShape(9.dp))
                    .background( Brush.verticalGradient(
                        listOf(
                            Color(0xffE000FF),
                            Color(0xffE000FF),
                            Color(0xFF7700FF),
                            Color(0xFF7700FF),
                        )
                    ))
                    .animateContentSize()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // increase Button
            Button(onClick = {
                if (progressCount < 10) {
                    progressCount += 2
                }
            }) {
                Text(text = "Обновить")
            }
        }
    }
}