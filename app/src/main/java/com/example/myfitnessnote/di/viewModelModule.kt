package com.example.myfitnessnote.di

import com.example.myfitnessnote.presentetion.viewmodel.DayFinalViewModel
import com.example.myfitnessnote.presentetion.viewmodel.DaysViewModel
import com.example.myfitnessnote.presentetion.viewmodel.ExerciseViewModel
import com.example.myfitnessnote.presentetion.viewmodel.TrainingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DaysViewModel(interactor = get())
    }

    viewModel { (dayId: Int) ->
        ExerciseViewModel(interactor = get(), dayId = dayId)
    }

    viewModel { (dayId: Int) ->
        DayFinalViewModel(interactor = get(), dayId = dayId)
    }
    viewModel {
        TrainingViewModel(interactor = get())
    }
}