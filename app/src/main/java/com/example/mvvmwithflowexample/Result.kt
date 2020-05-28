package com.example.mvvmwithflowexample

sealed class Result<out R>{
    data class Success<out T>(val data : T) : Result<T>()
    object Loading : Result<Nothing>()
    object Error : Result<Nothing>()
}