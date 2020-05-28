package com.example.mvvmwithflowexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithflowexample.Result
import com.example.mvvmwithflowexample.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map

class WeatherForecastDataStreamViewModel(repository: WeatherRepository) : ViewModel(){
    private val _weatherForecast = repository.fetchWeatherForecastRealTime()
        .map{
            //do some operation
            //operation will be done in the scope of this flow collected
            delay(1000)
            it
        }
        .asLiveData(Dispatchers.Default + viewModelScope.coroutineContext)

    val weatherForecast: LiveData<Result<Int>>
        get() = _weatherForecast
}