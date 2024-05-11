package com.example.myfitnessnote.app

import android.app.Application
import com.example.myfitnessnote.di.dataModule
import com.example.myfitnessnote.di.domainModule
import com.example.myfitnessnote.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}