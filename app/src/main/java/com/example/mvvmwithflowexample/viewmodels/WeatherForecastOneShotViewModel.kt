package com.example.mvvmwithflowexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithflowexample.Result
import com.example.mvvmwithflowexample.repository.WeatherRepository

class WeatherForecastOneShotViewModel(repository: WeatherRepository) : ViewModel() {
    private val _weatherForecast =
        repository.fetchWeatherForecast().asLiveData(viewModelScope.coroutineContext)

    val weatherForecast : LiveData<Result<Int>>
        get() = _weatherForecast
}