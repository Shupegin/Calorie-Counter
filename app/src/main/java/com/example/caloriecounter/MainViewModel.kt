package com.example.caloriecounter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caloriecounter.network.ApiFactory
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    init{
        loadPropertyFoods()
    }

    fun loadPropertyFoods(){
        viewModelScope.launch {
            val response = ApiFactory.getApi().loadInfoFoods(food_id = 666765)
            Log.d("TEST","TEST = $response")
        }

    }

}