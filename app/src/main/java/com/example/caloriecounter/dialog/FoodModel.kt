package com.example.caloriecounter.dialog

import androidx.room.Entity

@Entity(tableName = "food_name_list")
data class FoodModel(
    var food_id: String? = null,
    var dataCurrent: String? = null,
    var food: String? = null,
    var desctription: String? = null,
    var calories: Int? = null,
    var error: Int? = null
)
