package cal.calor.caloriecounter.dialog


//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun dialog(dialogState: MutableState<Boolean>,
//           viewModel: MainViewModel,
//){
//    var userfood by remember { mutableStateOf("") }
//        AlertDialog(
//            onDismissRequest = {
//                dialogState.value = false
//            },
//            title = { Text(text = "Дата: ${viewModel.getCurrentDate()} ") },
//
//            text = {
//
//                OutlinedTextField(
//                    value = userfood,
//                    onValueChange = {userfood = it},
//                    label = {
//                        Text(
//                            text = "что ел?",
//                            style = androidx.compose.ui.text.TextStyle(
//                                color = Color.Black,
//                            )
//                        )
//                    },
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        focusedBorderColor = Color.Red,
//                        unfocusedBorderColor = Color.Green
//                    )
//                )
//            },
//
//            dismissButton = {
//                Button(onClick = {
//                    dialogState.value = false},
//
//                    ) {
//                    Text(text = "cancel", color = Color.Black)
//                }
//            },
//
//            confirmButton = {
//                Button(onClick = {
//                    val foodModel = FoodModel(food = userfood)
//                    viewModel.addInfoFoodBtn(foodModel)
//                    dialogState.value = false},
//
//                    ) {
//                    Text(text = "Ok", color = Color.Black)
//                }
//            }
//        )
//}
