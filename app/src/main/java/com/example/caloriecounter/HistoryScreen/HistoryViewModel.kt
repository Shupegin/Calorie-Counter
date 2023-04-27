package com.example.caloriecounter.HistoryScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.caloriecounter.database.AppDatabase

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    val foodListDAO = db.foodsInfoDao().getFoodsList()


}