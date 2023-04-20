package com.example.caloriecounter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.caloriecounter.dialog.FoodModel

@Dao
interface FoodsInfoDao {
    @Query("SELECT * FROM food_name_list")
    fun getFoodsList() : LiveData<List<FoodModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList (listFood : List<FoodModel>)

    @Query("DELETE FROM food_name_list")
    suspend fun delete()
}