package com.github.zuccini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.zuccini.ui.theme.ZucciniTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AppActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZucciniTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val viewModel = viewModel(modelClass = AppViewModel::class.java)
    var selectedFruit = viewModel.selectedFruit
    var name by remember(selectedFruit.fruitId) { mutableStateOf(selectedFruit.fruitName) }
    var location by remember(selectedFruit.fruitId) { mutableStateOf(selectedFruit.location) }
    var inDescription by remember(selectedFruit.fruitId) { mutableStateOf(selectedFruit.description) }
    Column(modifier = Modifier.padding(10.dp)) {
        FruitSpinner(fruits = viewModel.fruits) {
            viewModel.selectedFruit = it
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.fruit_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text(stringResource(R.string.location)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = inDescription,
            onValueChange = { inDescription = it },
            label = { Text(stringResource(R.string.description)) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FruitSpinner(fruits: List<Fruit>, setSelectedFruit: (Fruit) -> Unit) {
    var fruitsText by remember { mutableStateOf(fruits.first().fruitName) }
    var expanded by remember { mutableStateOf(false) }
    val viewModel = viewModel(modelClass = AppViewModel::class.java)
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            Modifier
                .padding(24.dp)
                .clickable {
                    expanded = !expanded
                }
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = fruitsText, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                fruits.forEach { fruit ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        setSelectedFruit(fruit)
                        fruitsText = fruit.fruitName
                    }) {
                        Text(text = fruit.toString())
                    }
                }
            }
        }
    }
}
