package com.example.mvvmwithflowexample

import android.app.Application
import com.example.mvvmwithflowexample.modules.appModules
import com.example.mvvmwithflowexample.modules.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(appModules, repositoryModule))
        }
    }
}