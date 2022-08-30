package com.github.zuccini

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

val fruitList =
    listOf(
        Fruit("Apple", "1121333", "New Zealand", "Vitamin A"),
        Fruit("Apricot", "2000003", "Germany", "Vitamin A"),
        Fruit("Avocado", "300004", "Italy", "Vitamin C")
    )

@HiltViewModel
class AppViewModel @Inject constructor() : ViewModel() {
    val fruits by mutableStateOf(fruitList)
    val location by mutableStateOf(fruitList.first().location)
    var selectedFruit by mutableStateOf(fruitList.first())
}
