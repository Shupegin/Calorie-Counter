package com.example.caloriecounter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caloriecounter.dialog.FoodMapper
import com.example.caloriecounter.dialog.FoodModel
import com.example.caloriecounter.network.ApiFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : ViewModel() {
    val mapper = FoodMapper()
    var food_id : String? = "321312"
    var token : String? = ""


    private val _addInfoFood = MutableLiveData<MutableList<FoodModel>>()
    val addInfoFood : MutableLiveData<MutableList<FoodModel>> = _addInfoFood//_addInfoFood

    init {
        authorizationRequest()
        getCurrentDate()
    }

    @SuppressLint("SuspiciousIndentation")
    fun authorizationRequest() {
        viewModelScope.launch {
            val clientId = "9bf375c35df743e7be742724d0a1fd31";
            val clientSecret= "0e8023668fa943b3ab9555065c53be4e";
            var credentials = "OWJmMzc1YzM1ZGY3NDNlN2JlNzQyNzI0ZDBhMWZkMzE6MGU4MDIzNjY4ZmE5NDNiM2FiOTU1NTA2NWM1M2JlNGU="
            val response = ApiFactory.getApiAuthorization().requestAuthorization(auth = "Basic $credentials")

            token = response.accessToken
        }
    }
    @SuppressLint("SuspiciousIndentation")
 fun loadSearchFood(foodModel : FoodModel) {
     val nameFood : String = foodModel.food.toString()
        viewModelScope.launch {
            val response = ApiFactory.getApi(token.toString()).loadSearchFoods(search_expression = nameFood)
            val foodModelList = mapper.mapResponseToPosts(response)

            var calories = 0
            for ( item in foodModelList){
                    food_id = item.food_id
                    var desctription = textFilter(item.desctription.toString())
                    calories += desctription
            }

            calories /= foodModelList.size

            val array = addInfoFood.value?.toMutableList() ?: mutableListOf()

            foodModel.calories = calories
            foodModel.dataCurrent = getCurrentDate()
            array.apply {
             add(foodModel)
            }
            _addInfoFood.value = array
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



 fun addInfoFoodBtn(foodModel : FoodModel) {
     loadSearchFood(foodModel)
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyy")
        return dateFormat.format(Date())
    }
}