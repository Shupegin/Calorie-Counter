package com.example.caloriecounter.network

import com.example.caloriecounter.SearchPojoFoods
import com.example.caloriecounter.pojo.AuthorizationPassword.AuthorizationPassword
import retrofit2.http.*


interface ApiService {
    @GET("server.api")
    suspend fun loadSearchFoods(
        @Query("method") method: String = "foods.search.v2",
        @Query("format") format: String = "json",
        @Query("search_expression") search_expression: String
    ): SearchPojoFoods

    @FormUrlEncoded
    @POST("token")
    suspend fun requestAuthorization(
        @Field("grant_type") grant_type: String = "client_credentials",
        //@Field("scope") scope: String = "basic",
        @Field("scope") scope: String = "basic premier barcode",
        @Header("Authorization") auth: String
    ) : AuthorizationPassword
}

