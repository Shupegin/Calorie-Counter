package com.example.caloriecounter

fun main() {
    val text  = "Per 1 sandwich - Calories: 220kcal |Fat: 7.00g | Carbs: 23.00g | Protein: 19.00g"
    textFilter(text)
}

fun textFilter(text: String){
    val index = text.lastIndexOf("-")
    val filteredText = text.substring(index + 2).substringBefore("|")
    println(filteredText)
    val filterText = filteredText.filter { it.isDigit() }
    println(filterText)
}

