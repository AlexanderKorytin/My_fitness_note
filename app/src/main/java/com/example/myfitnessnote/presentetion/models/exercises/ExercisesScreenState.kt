package com.example.myfitnessnote.presentetion.models.exercises

import com.example.myfitnessnote.domain.models.ExerciseItem

sealed interface ExercisesScreenState {
    data object Loading : ExercisesScreenState
    data class Content(val data: List<ExerciseItem>, val counter: Int) : ExercisesScreenState
    data object Error : ExercisesScreenState
}