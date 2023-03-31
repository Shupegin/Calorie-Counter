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
                val token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVGQUQ4RTE5MjMwOURFRUJCNzBCMzU5M0E2MDU3OUFEMUM5NjgzNDkiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJYNjJPR1NNSjN1dTNDeldUcGdWNXJSeVdnMGsifQ.eyJuYmYiOjE2ODAyNTQ5NjUsImV4cCI6MTY4MDM0MTM2NSwiaXNzIjoiaHR0cHM6Ly9vYXV0aC5mYXRzZWNyZXQuY29tIiwiYXVkIjoiYmFzaWMiLCJjbGllbnRfaWQiOiI5YmYzNzVjMzVkZjc0M2U3YmU3NDI3MjRkMGExZmQzMSIsInNjb3BlIjpbImJhc2ljIl19.RlRFS9eD7XRexb8O0iVijePiir3yBAQazohUkVCGOXieuR-otvidupZ3myiwCnd91xRrbzuzJ11AjY2RXkYGb1BFdUoMeJJsr38C8g0NcqbHFIz1rLUF_emIEi2ZUiQ7kskw8lCUrSqaGM9mfwoU9EgRKD8dpPh1WNLAy3rO2SNb35o4ldRTd4OYUNt9JFIYJgPrPGjnBD93obghzWT-2HW7dIn6vi7_lrUJz2enM6QcJW2dogwxRHcnrVtAtcrVNuOBgzFI2RHesWvj4xBf40ztzMUW2nhpBHroQ7gL0hm6Q8n53lLL31R3mzjkWdRm43IQREcZaCaES2ObjWWu2g"

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