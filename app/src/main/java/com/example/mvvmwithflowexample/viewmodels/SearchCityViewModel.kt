package com.example.mvvmwithflowexample.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

class SearchCityViewModel : ViewModel(){
    val cityList = listOf(
        "Los Angeles", "Chicago", "Indianapolis", "Phoenix", "Houston",
        "Denver", "Las Vegas", "Philadelphia", "Portland", "Seattle"
    )

    val cityFilterChannel = ConflatedBroadcastChannel<String>()

    val cityFilterFlow: Flow<List<String>> = cityFilterChannel
        .asFlow()
        .map {
            // Filter cities with new value
            val filteredCities = filterCities(it)

            // Do some heavy work
            delay(500)

            // Return the filtered list
            filteredCities
        }

    override fun onCleared() {
        super.onCleared()

        // Close the channel when ViewModel is destroyed
        cityFilterChannel.close()
    }

    private fun filterCities(key: String): List<String> {
        return cityList.filter {
            it.contains(key)
        }
    }
}