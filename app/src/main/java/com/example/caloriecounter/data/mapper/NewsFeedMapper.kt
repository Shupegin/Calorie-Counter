package com.example.caloriecounter.dialog


import android.util.Log
import com.example.caloriecounter.SearchPojoFoods

class FoodMapper {

    fun mapResponseToPosts(responseDto:SearchPojoFoods ): List<FoodModel> {
        val result = mutableListOf<FoodModel>()
        val posts = responseDto.foods?.food

        if (posts != null) {
            for (post in posts) {
                val foodModel = FoodModel(
                    food = post.foodName,
                    desctription = post.foodDescription
                )
                result.add(foodModel)
            }
        }else{
            val foodModel = FoodModel(
                error = responseDto.error?.code,
            )
            result.add(foodModel)
        }
        return result
    }
}
