package com.example.myfitnessnote.domain.models

sealed interface ExercisesListResult {
    data object ErrorState: ExercisesListResult
    data object ErrorUnKnow: ExercisesListResult
    data class Content(val data: List<ExerciseItem>) : ExercisesListResult
}