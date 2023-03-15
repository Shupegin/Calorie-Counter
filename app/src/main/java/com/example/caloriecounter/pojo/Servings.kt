package com.example.caloriecounter

import com.google.gson.annotations.SerializedName


data class Servings (

  @SerializedName("serving" ) var serving : Serving? = Serving()

)