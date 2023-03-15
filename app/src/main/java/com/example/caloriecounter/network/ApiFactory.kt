package com.example.caloriecounter.network

import com.example.caloriecounter.JsonPojoFood
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class ApiFactory {
    companion object {
        private const val BASE_URL = "https://platform.fatsecret.com/rest/"

        fun getApi(): ApiService {
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val token =
                    "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVGQUQ4RTE5MjMwOURFRUJCNzBCMzU5M0E2MDU3OUFEMUM5NjgzNDkiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJYNjJPR1NNSjN1dTNDeldUcGdWNXJSeVdnMGsifQ.eyJuYmYiOjE2Nzg3Nzg2NTIsImV4cCI6MTY3ODg2NTA1MiwiaXNzIjoiaHR0cHM6Ly9vYXV0aC5mYXRzZWNyZXQuY29tIiwiYXVkIjoiYmFzaWMiLCJjbGllbnRfaWQiOiI5YmYzNzVjMzVkZjc0M2U3YmU3NDI3MjRkMGExZmQzMSIsInNjb3BlIjpbImJhc2ljIl19.s8zUpaxX3C5YZwxTBWFRfuVQFuhGmdLz_Z0-Fi0bUmvmYMJRCRslK_h5AdX_ySbBSw17UjH3VBwxdFxmrofzKgEazTWeqV7kdVS2FEr12lXZBRtzFdI9l7oDiEOo7qfUM9BF9tqQwKk3fqkYMggGywZAzWhbm395WjfuFQp13nZL_YO-1M_ieum-3e0_iYfLDlyTJ1SxK-80WaeaD9OiydIg-care0CoMw8nZFmJKCvFu2QmP8R5Ki40YDkYbBIkUDSQczgZlujky1r6l2ej_AynJZKcc29jAmeSA4NFSqeYe-9M8KyS_VOH3nMQ5VCOvlv3gQ9cU6GW5Fwm6OXkEw"
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