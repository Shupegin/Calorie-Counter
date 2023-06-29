package com.example.caloriecounter.dialog


import com.example.caloriecounter.SearchPojoFoods
import com.example.caloriecounter.pojo.FoodModel

class FoodMapper {

    fun mapResponseToPosts(responseDto: SearchPojoFoods): List<FoodModel> {
        val result = mutableListOf<FoodModel>()
        val posts = responseDto.foods?.result?.food

        if (posts != null) {
            for (post in posts) {

                for (item in post.servings!!.serving){
                    val foodModel = FoodModel(
                        food_id = post.foodId?.toInt()!!,
                        calories = item.calories?.toInt()
                        )
                    result.add(foodModel)
                }

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
