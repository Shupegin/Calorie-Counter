package com.example.caloriecounter

import com.google.gson.annotations.SerializedName


data class Foods (

  @SerializedName("food"          ) var food         : ArrayList<Food> = arrayListOf(),
  @SerializedName("max_results"   ) var maxResults   : String?         = null,
  @SerializedName("page_number"   ) var pageNumber   : String?         = null,
  @SerializedName("total_results" ) var totalResults : String?         = null

)