package com.example.caloriecounter

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.caloriecounter.RegistrationScreen.User
import com.example.caloriecounter.database.AppDatabase

import com.example.caloriecounter.dialog.FoodMapper
import com.example.caloriecounter.dialog.FoodModel
import com.example.caloriecounter.network.ApiFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@RequiresApi(Build.VERSION_CODES.O)

class MainViewModel(application: Application): AndroidViewModel(application){
    val mapper = FoodMapper()
    var food_id : Int? = 321312
    var token : String? = ""

    private val db = AppDatabase.getInstance(application)
    val foodListDAO = db.foodsInfoDao().getFoodsList()
    private val _historyCalories : MutableLiveData<Int> = MutableLiveData()
    val addHistoryCalories : MutableLiveData<Int> = _historyCalories

    private val _day : MutableLiveData<String> = MutableLiveData()
    val addDay : MutableLiveData<String> = _day

    private var firebaseDatabase : FirebaseDatabase? = null
    private var userReference : DatabaseReference? = null
    private var auth:  FirebaseAuth? = null


    private val _clientID : MutableLiveData<String> = MutableLiveData()
    val client : MutableLiveData<String> =  _clientID

    private val _imageQR : MutableLiveData<Bitmap> = MutableLiveData()
    val imageQR : MutableLiveData<Bitmap> = _imageQR


    init {
        authorizationRequest()
        getCurrentDate()
        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        userReference = firebaseDatabase?.getReference("calories")
        auth?.addAuthStateListener{
            _clientID.value = it.uid
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun authorizationRequest() {
        viewModelScope.launch {
            val clientId = "9bf375c35df743e7be742724d0a1fd31";
            val clientSecret= "0e8023668fa943b3ab9555065c53be4e";
            var credentials = "OWJmMzc1YzM1ZGY3NDNlN2JlNzQyNzI0ZDBhMWZkMzE6MGU4MDIzNjY4ZmE5NDNiM2FiOTU1NTA2NWM1M2JlNGU="
            val response = ApiFactory.getApiAuthorization().requestAuthorization(auth = "Basic $credentials")
            Log.d("TESTER","request = ${response.accessToken}")

            token = response.accessToken
        }
    }
    @SuppressLint("SuspiciousIndentation")
 fun loadSearchFood(foodModel : FoodModel) {
     val nameFood : String = foodModel.food.toString()
        viewModelScope.launch {
            val response = ApiFactory.getApi(token.toString()).loadSearchFoods(search_expression = nameFood)
            val foodModelList = mapper.mapResponseToPosts(response)
            Log.d("TESTER","request = ${response.foods}")
            var calories = 0
            for (item in foodModelList){
                    food_id = item.food_id
                try {
                    var desctription = textFilter(item.desctription.toString())
                    calories += desctription
                }catch (e : java.lang.Exception){

                }
            }
//            calories /= foodModelList.size
            foodModel.calories = 200
            foodModel.dataCurrent = getCurrentDate()
            val listFood = ArrayList<FoodModel>()
            listFood.add(foodModel)
            db.foodsInfoDao().insertFoodList(listFood)

            auth?.addAuthStateListener{
                it.uid?.let { it1 -> userReference?.child(it1)?.push()?.setValue(foodModel) }
            }
        }
    }


   fun getCalories(listFood : List<FoodModel>) : Int{
       return listFood.sumOf { it.calories ?: 0 }
    }

    fun sendSelectedOptionText(selectedOptionText : String,listFood: List<FoodModel>){
        when(selectedOptionText){
            "День" -> getCaloriesOneDay(listFood)
            "Неделя" -> getCaloriesWeek()
            "Две недели" -> Log.d("History","3")
            "Месяц"  -> Log.d("History","4")

        }
        _day.value = selectedOptionText
    }

    fun getCaloriesOneDay(listFood : List<FoodModel>){
        var calories = 0
        for(item in listFood){
            if (item.dataCurrent == getCurrentDate()){
                calories += item.calories ?: 0
            }
        }
        _historyCalories.value = calories
    }

    fun getCaloriesWeek(){
        _historyCalories.value = 0
    }

    fun textFilter(text: String) : Int{
        val index = text.lastIndexOf("-")
        val filteredText = text.substring(index + 2).substringBefore("|")
        val filterText = filteredText.filter { it.isDigit() }
        return filterText.toInt()
    }

 fun addInfoFoodBtn(foodModel : FoodModel) {
     loadSearchFood(foodModel)
    }

    @SuppressLint("SimpleDateFormat")
     fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyy")
        return dateFormat.format(Date())
    }

    fun generateQR(ui : String){
        try {
            val barcodeEncode = BarcodeEncoder()
            val bitmap : Bitmap = barcodeEncode.encodeBitmap(ui, BarcodeFormat.QR_CODE, 750, 750)
                _imageQR.value = bitmap
        } catch (e: WriterException){}

    }
}