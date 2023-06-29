package com.example.caloriecounter.pojo.SearchFood

import com.example.caloriecounter.Food
import com.google.gson.annotations.SerializedName

data class ResultFood (
    @SerializedName("food") var food: ArrayList<Food> = arrayListOf()
)