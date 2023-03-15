package com.example.caloriecounter

import com.google.gson.annotations.SerializedName


data class JsonPojoFood (

  @SerializedName("food" ) var food : Food? = Food()

)