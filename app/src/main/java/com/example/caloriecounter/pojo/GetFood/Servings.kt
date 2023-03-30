package com.example.caloriecounter.pojo.GetFood

import com.google.gson.annotations.SerializedName


data class Servings (

  @SerializedName("serving" ) var serving : Serving? = Serving()

)