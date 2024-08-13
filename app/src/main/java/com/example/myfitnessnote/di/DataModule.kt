package com.example.myfitnessnote.di

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myfitnessnote.data.db.AppDataBase
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
        DaysExercisesSharedPrefStorage(
            androidContext(),
            sharedPref = get(),
            json = get(),
            dataBase = get()
        )
    }
    factory {
        Gson()
    }
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, "database.db")
            .createFromAsset("db/database.db")
            .build()
    }
}
const val APP_SETTINGS_PREF_KEY = "App settings"