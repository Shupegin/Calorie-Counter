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
                val token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVGQUQ4RTE5MjMwOURFRUJCNzBCMzU5M0E2MDU3OUFEMUM5NjgzNDkiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJYNjJPR1NNSjN1dTNDeldUcGdWNXJSeVdnMGsifQ.eyJuYmYiOjE2ODAxNjg4NzUsImV4cCI6MTY4MDI1NTI3NSwiaXNzIjoiaHR0cHM6Ly9vYXV0aC5mYXRzZWNyZXQuY29tIiwiYXVkIjoiYmFzaWMiLCJjbGllbnRfaWQiOiI5YmYzNzVjMzVkZjc0M2U3YmU3NDI3MjRkMGExZmQzMSIsInNjb3BlIjpbImJhc2ljIl19.SuURSMf5OvxkaoGMYFBmImhRytaisp0ezJG_5fgjtpu6g0HzdbQ4ZgcfwZUrsLplU3ifKR0a1fSqF-z3PCwJBJbAoW6YHivBvCCbzMbv2nTKZ3yejOTdb39P8rdmgr5MvNdQOzMMJOKPwvcuubV_f967ANLxBhGunzOI0DXDfgruF9DftEeRaM8DR9h1q2CI5W9qT7vQqwD6J-S5Ir0GT38-on2fYc3e3yIy3w7pfYsN8HYHiRevA2MZ_p1RAa4OezlCXh-N4PAs7DK79keGLBkAMjdcbfns-fpcoytFjLHc4mHUMdb9lxFAsVVU3ytL2bMVJkaBnwMGGkuzdNU8oQ"

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