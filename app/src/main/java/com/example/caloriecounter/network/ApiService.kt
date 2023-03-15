package com.example.caloriecounter.network

import com.example.caloriecounter.JsonPojoFood
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("server.api")
    suspend fun loadInfoFoods(@Query("method") method: String = "food.get.v2",
                              @Query("format") format: String = "json",
                              @Query("food_id") food_id: Int
    ) : JsonPojoFood
}