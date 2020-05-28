/**
 * write by nhatsieunhan999@gmail.com
 * 05/27/2020
 */
package com.example.mvvmwithflowexample.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvmwithflowexample.repository.Constants.SHARED_PREFERENCES_NAME
import com.example.mvvmwithflowexample.repository.ThemeDataSource
import com.example.mvvmwithflowexample.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherRepository() }
    single { provideSharedPreference(get()) }
    single { ThemeDataSource(get()) }
}


fun provideSharedPreference(context: Context): SharedPreferences =
    context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)