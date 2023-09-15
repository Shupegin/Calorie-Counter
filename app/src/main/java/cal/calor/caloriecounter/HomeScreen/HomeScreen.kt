package cal.calor.caloriecounter

import android.annotation.SuppressLint


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cal.calor.caloriecounter.ui.theme.Сoral
import com.example.caloriecounter.cardFood

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onItem: () -> Unit,
    paddingValues: PaddingValues
){
    Box(Modifier

    ){
        val foodList = viewModel.foodListDAO.observeAsState(listOf())
        val list = foodList.value.groupBy { it.dataCurrent }

      LazyColumn(modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 55.dp),
      ){


          list?.forEach{(dataCurrent,listFood)->

              stickyHeader{
                  Box(modifier = Modifier
                      .fillMaxWidth()
                      .padding(1.dp)
                      .background(color = Сoral),
                      contentAlignment = Alignment.Center
                  ){
                      Text(text = dataCurrent.toString(), style = MaterialTheme.typography.h6)
                  }
              }
              items(listFood){foodModel ->
                  val dismissState = rememberDismissState()
                  if(dismissState.isDismissed(DismissDirection.EndToStart)){
                      viewModel.deleteFood(foodModel)
                  }
                  SwipeToDismiss(
                      state = dismissState,
                      directions = setOf(DismissDirection.EndToStart)

                      , background = {
                          Box (
                              modifier = Modifier
                                  .padding(16.dp)
                                  .fillMaxSize()
                                  .background(Color.Red.copy(alpha = 0.5f)),
                              contentAlignment = Alignment.BottomEnd
                          ){
                              Text(modifier = Modifier.padding(16.dp), text = "Удалить элемент")
                          }
                      }
                  ) {
                      cardFood(foodModel = foodModel)
                  }

              }
            item() {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    contentAlignment = Alignment.BottomEnd,
                    ) {
                    val totalCalories = viewModel.getCalories(listFood)
                    Text(text = "Сумма калорий = $totalCalories ", modifier = Modifier.background(color = Color.Cyan) )
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

