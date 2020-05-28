package com.example.mvvmwithflowexample.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mvvmwithflowexample.Result
import com.example.mvvmwithflowexample.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class WeatherForecastDataStreamFlowViewModel(
    repository: WeatherRepository
) : ViewModel() {
    private val _weatherForecast = repository.fetchWeatherForecastRealTime()
        .onStart {
            emit(Result.Loading)
        }
        .distinctUntilChanged()
        .filter {
            delay(200)

            if (it is Result.Success) {
                it.data < 10
            } else true
        }
        .buffer()
        .map {
            delay(500)

            if (it is Result.Success) {
                val fDegree = convertCelsiusToFahrenheit(it.data)
                Result.Success(fDegree)
            } else {
                it
            }
        }
        .transform {
            if (it is Result.Success && it.data % 2 == 0) {
                val evenDegree = it.data
                emit(Result.Success(evenDegree))
            } else
                emit(it)
        }.onEach {
            //do something like save data , cache
            Log.d("tahn", "$it has been process")
        }.flowOn(Dispatchers.Default)
        .catch { t ->
        }

    private val _weatherForecastOtherDataSource = repository.fetchWeatherForecastRealTimeDataSource()

    val weatherForecast: Flow<Result<Int>>
        get() = merge(_weatherForecast, _weatherForecastOtherDataSource)


    private fun convertCelsiusToFahrenheit(c: Int) = c * 9 / 5 + 32
}