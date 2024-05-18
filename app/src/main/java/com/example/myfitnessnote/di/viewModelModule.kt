package com.example.myfitnessnote.di

import com.example.myfitnessnote.presentetion.vievmodel.DaysViewModel
import com.example.myfitnessnote.presentetion.vievmodel.ExerciseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DaysViewModel(interactor = get())
    }

    viewModel {(dayId: Int) ->
        ExerciseViewModel(interactor = get(), dayId = dayId)
    }
}