package com.example.caloriecounter

import com.google.gson.annotations.SerializedName


data class SearchPojoFoods (

  @SerializedName("foods" ) var foods : Foods? = Foods()

)