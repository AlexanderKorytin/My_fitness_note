package com.example.myfitnessnote.di

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage
import com.example.myfitnessnote.data.storage.impl.DaysExercisesSharedPrefStorage
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(APP_SETTINGS_PREF_KEY, AppCompatActivity.MODE_PRIVATE)
    }
    single<DaysExercisesStorage> {
        DaysExercisesSharedPrefStorage(androidContext(), sharedPref = get(), json = get())
    }
    factory {
        Gson()
    }
}
const val APP_SETTINGS_PREF_KEY = "App settings"