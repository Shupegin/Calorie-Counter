package com.example.caloriecounter

import com.example.caloriecounter.pojo.SearchFood.ApiError
import com.google.gson.annotations.SerializedName


data class SearchPojoFoods (
  @SerializedName("foods_search") var foods : Foods? = null,
  @SerializedName("error") var error : ApiError?  = null
)

