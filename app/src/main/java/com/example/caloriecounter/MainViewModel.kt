package com.example.caloriecounter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caloriecounter.dialog.FoodMapper
import com.example.caloriecounter.dialog.FoodModel
import com.example.caloriecounter.network.ApiFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Handler

class MainViewModel : ViewModel() {


    val mapper = FoodMapper()

    var food_id : String? = "321312"

    init{
        loadSearchFood()
    }

    fun loadSearchFood()  {
        viewModelScope.launch {
            val response = ApiFactory.getApi().loadSearchFoods(search_expression = "Sausage sandwich")
            val foodModelList = mapper.mapResponseToPosts(response)
            var calories = 0
            for ( i in foodModelList){
                food_id = i.food_id

                var desctription = textFilter(i.desctription.toString())
                calories += desctription
            }
            calories /= foodModelList.size

            Log.d("TRT","Еда2  = ${calories}")
        }

    }

    fun textFilter(text: String) : Int{

        val index = text.lastIndexOf("-")
        val filteredText = text.substring(index + 2).substringBefore("|")
        val filterText = filteredText.filter { it.isDigit() }

        return filterText.toInt()
    }

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem> = _selectedNavItem

    fun selectNavItem(item: NavigationItem) {
        _selectedNavItem.value = item
    }

    private val _addInfoFood = MutableLiveData<ArrayList<FoodModel>>()
    val addInfoFood : MutableLiveData<ArrayList<FoodModel>> = _addInfoFood

    fun addInfoFoodBtn(foodModel : FoodModel) {
        val array = _addInfoFood.value ?: ArrayList()
        array.add(foodModel)
        _addInfoFood.value = array
    }


}