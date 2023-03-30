package com.example.caloriecounter.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Query

class ApiFactory {
    companion object {
        private const val BASE_URL = "https://platform.fatsecret.com/rest/"

        fun getApi(): ApiService {
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVGQUQ4RTE5MjMwOURFRUJCNzBCMzU5M0E2MDU3OUFEMUM5NjgzNDkiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJYNjJPR1NNSjN1dTNDeldUcGdWNXJSeVdnMGsifQ.eyJuYmYiOjE2Nzk2MzI4OTIsImV4cCI6MTY3OTcxOTI5MiwiaXNzIjoiaHR0cHM6Ly9vYXV0aC5mYXRzZWNyZXQuY29tIiwiYXVkIjoiYmFzaWMiLCJjbGllbnRfaWQiOiI5YmYzNzVjMzVkZjc0M2U3YmU3NDI3MjRkMGExZmQzMSIsInNjb3BlIjpbImJhc2ljIl19.BEeZEOEFK3rebTYJxWnXoTMHhmmZj3MIh9L6_kwCt06gQH6hCqgQ7vms3Xyi0-H4nh96b-3qCkJuD3Qw0i21zPWBYO9mlSF1Mx9J8XMdDNvQK2D6BuqTz5boCmQhabr1VP4PSPYcVtBwLBdFas1wsOJuQR-6zM8tR7OJjQsjQor2phgYWJI10sTDEyT9CsV0bdPlditkloKUVzuPk_CVOm76Ll2Vh69Ep83lkG0w_wMgmxhn3Kl6VOLJ8uf3b4Vx62tz71zjxEExyVarISOqPvTPzLUdNsw88sLxAyy7d02-ERtZTK2QuZtapdJi4ad5_1v4Ji18mFDO95cB0nbJGg"

                val request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer ${token}")
                        .build()
                chain.proceed(request)
            }.build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create()
        }

    }
/*
    private interface ApiService {
        @GET
        suspend fun loadInfoFoods(@Query("method") method: String = "food.get.v2", @Query("format") format: String = "json",
            @Query("food_id") food_id: Int
        ) : JsonPojoFood
    }

 */
}