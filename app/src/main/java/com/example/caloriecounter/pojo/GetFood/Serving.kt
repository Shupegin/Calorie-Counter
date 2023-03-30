package com.example.caloriecounter.pojo.GetFood

import com.google.gson.annotations.SerializedName


data class Serving (

  @SerializedName("calories"                ) var calories               : String? = null,
  @SerializedName("carbohydrate"            ) var carbohydrate           : String? = null,
  @SerializedName("cholesterol"             ) var cholesterol            : String? = null,
  @SerializedName("fat"                     ) var fat                    : String? = null,
  @SerializedName("fiber"                   ) var fiber                  : String? = null,
  @SerializedName("measurement_description" ) var measurementDescription : String? = null,
  @SerializedName("number_of_units"         ) var numberOfUnits          : String? = null,
  @SerializedName("potassium"               ) var potassium              : String? = null,
  @SerializedName("protein"                 ) var protein                : String? = null,
  @SerializedName("saturated_fat"           ) var saturatedFat           : String? = null,
  @SerializedName("serving_description"     ) var servingDescription     : String? = null,
  @SerializedName("serving_id"              ) var servingId              : String? = null,
  @SerializedName("serving_url"             ) var servingUrl             : String? = null,
  @SerializedName("sodium"                  ) var sodium                 : String? = null,
  @SerializedName("sugar"                   ) var sugar                  : String? = null

)