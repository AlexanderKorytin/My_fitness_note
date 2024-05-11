package com.example.myfitnessnote.di

import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage
import com.example.myfitnessnote.data.storage.impl.DaysExercisesStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<DaysExercisesStorage> {
        DaysExercisesStorageImpl(androidContext())
    }
}