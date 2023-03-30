package com.example.caloriecounter.network

import com.example.caloriecounter.SearchPojoFoods
import com.example.caloriecounter.pojo.GetFood.JsonPojoFood
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("server.api")
    suspend fun loadInfoFoods(@Query("method") method: String = "food.get.v2",
                              @Query("format") format: String = "json",
                              @Query("food_id") food_id: Int
    ) : JsonPojoFood


    @GET("server.api")
    suspend fun loadSearchFoods(@Query("method") method: String = "foods.search.v2",
                              @Query("format") format: String = "json",
                              @Query("search_expression") search_expression : String
    ) : SearchPojoFoods
}