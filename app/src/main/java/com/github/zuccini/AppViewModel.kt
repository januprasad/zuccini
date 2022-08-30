package com.github.zuccini

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.zuccini.Fruit

class AppViewModel() : ViewModel() {
    val location by mutableStateOf("")
    val fruits by mutableStateOf<List<Fruit>>(emptyList())
    val selectedFruit by mutableStateOf(Fruit())
}
