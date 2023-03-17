package com.example.caloriecounter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem> = _selectedNavItem

    fun selectNavItem(item: NavigationItem) {
        _selectedNavItem.value = item
    }
    private val  _clickFloatingActionButton = MutableLiveData<Boolean>()
    val clickFloatingActionButton = _clickFloatingActionButton
    fun clickFloatingAcBtn(click : Boolean){
        _clickFloatingActionButton.value = click
    }



}