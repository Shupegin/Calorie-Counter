package com.example.caloriecounter.pojo.GetFood

import com.google.gson.annotations.SerializedName


data class JsonPojoFood (

  @SerializedName("food" ) var food : Food? = Food()

)