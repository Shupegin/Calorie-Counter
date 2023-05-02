package com.example.caloriecounter.HistoryScreen

import android.util.Log
import android.widget.VideoView
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.caloriecounter.MainViewModel
import com.example.caloriecounter.ui.theme.Gray500


@Composable
fun VerticalProgressBar(viewModel: MainViewModel,
                        owner: LifecycleOwner
) {
    var progress by remember { mutableStateOf(0f) }
    var numberOfCalories  = viewModel.addHistoryCalories.observeAsState()

    if(numberOfCalories.value != null) {
        val caloriesPerDay = 2000
        val calories =  numberOfCalories.value!!.toFloat() / caloriesPerDay

        progress = calories
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
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xffE000FF),
                                Color(0xffE000FF),
                                Color(0xFF7700FF),
                                Color(0xFF7700FF),
                            )
                        )
                    )
                    .animateContentSize()
            )
        }
    }
}