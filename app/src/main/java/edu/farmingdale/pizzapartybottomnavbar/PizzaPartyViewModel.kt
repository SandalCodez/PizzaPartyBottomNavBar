package edu.farmingdale.pizzapartybottomnavbar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.ceil

class PizzaPartyViewModel : ViewModel() {

    private val _numPeopleInput = MutableStateFlow("")
    val numPeopleInput: StateFlow<String> = _numPeopleInput.asStateFlow()

    private val _hungerLevel = MutableStateFlow("Medium")
    val hungerLevel: StateFlow<String> = _hungerLevel.asStateFlow()

    private val _totalPizzas = MutableStateFlow(0)
    val totalPizzas: StateFlow<Int> = _totalPizzas.asStateFlow()

    val radioOptions = listOf("Light", "Medium", "Moderately Hungry", "Very hungry")

    fun updateNumPeople(input: String) {
        _numPeopleInput.value = input
    }

    fun updateHungerLevel(level: String) {
        _hungerLevel.value = level
    }

    fun calculateTotalPizzas() {
        val numPeople = _numPeopleInput.value.toIntOrNull() ?: 0
        _totalPizzas.value = calculateNumPizzas(numPeople, _hungerLevel.value)
    }

    private fun calculateNumPizzas(numPeople: Int, hungerLevel: String): Int {
        val slicesPerPizza = 8
        val slicesPerPerson = when (hungerLevel) {
            "Light" -> 2
            "Medium" -> 3
            "Moderately Hungry" -> 4
            "Very hungry" -> 5
            else -> 3
        }
        return ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt()
    }
}