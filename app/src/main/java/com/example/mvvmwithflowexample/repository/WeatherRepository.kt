/**
 * write by nhatsieunhan999@gmail.com
 * 05/27/2020
 */
package com.example.mvvmwithflowexample.repository

import com.example.mvvmwithflowexample.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository{

    /**
     * one shot request to get fake weather data
     */
    fun fetchWeatherForecast() = flow {
        emit(Result.Loading)
        delay(1000)
        emit(Result.Success((0..20).random()))
    }

    /**
     * get data stream of fake weather forecast data in real time
     */
    fun fetchWeatherForecastRealTime() : Flow<Result<Int>> = flow{
        emit(Result.Loading)
        while (true){
            delay(1000)
            emit(Result.Success((0..20).random()))
        }
    }

    /**
     * get data stream of fake weather forecast from another data source
     */
    fun fetchWeatherForecastRealTimeDataSource() = flow{
        emit(Result.Loading)
        while (true){
            delay(750)
            emit(Result.Success((0..20).random()))
        }
    }
}