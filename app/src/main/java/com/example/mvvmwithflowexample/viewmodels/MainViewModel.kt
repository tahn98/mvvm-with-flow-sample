package com.example.mvvmwithflowexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithflowexample.repository.Theme
import com.example.mvvmwithflowexample.repository.ThemeDataSource

class MainViewModel(private val themeDataSource: ThemeDataSource) : ViewModel(){
    private val _theme: LiveData<Theme> = themeDataSource
        .getTheme()
        .asLiveData(viewModelScope.coroutineContext)

    val theme: LiveData<Theme>
        get() = _theme

    fun setTheme(theme: Theme) {
        themeDataSource.setTheme(theme)
    }
}