package com.example.caloriecounter.dialog


import com.example.caloriecounter.SearchPojoFoods
import kotlin.math.absoluteValue

class FoodMapper {

    fun mapResponseToPosts(responseDto:SearchPojoFoods ): List<FoodModel> {
        val result = mutableListOf<FoodModel>()

        val posts = responseDto.foods?.food


        if (posts != null) {
            for (post in posts) {
                val foodModel = FoodModel(
                    food_id =  post.foodId,
                    food = post.foodName,
                    desctription = post.foodDescription
                    )
                result.add(foodModel)
            }
        }
        return result
    }
}