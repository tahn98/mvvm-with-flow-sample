/**
 * write by nhatsieunhan999@gmail.com
 * 05/27/2020
 */
package com.example.mvvmwithflowexample.modules

import com.example.mvvmwithflowexample.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel{
        WeatherForecastOneShotViewModel(get())
    }
    viewModel{
        WeatherForecastDataStreamViewModel(get())
    }
    viewModel {
        WeatherForecastDataStreamFlowViewModel(get())
    }
    viewModel {
        SearchCityViewModel()
    }
}