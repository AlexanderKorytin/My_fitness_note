package com.example.myfitnessnote.presentetion.models.exercises

sealed interface ExercisesIntent {
    data object RequestExercises : ExercisesIntent
    data object UpdateCounter : ExercisesIntent
    data object RepeatDay : ExercisesIntent
}