package com.example.myfitnessnote.domain.models

data class DayItem(
    val dayId: Int,
    val exercises: List<ExerciseItem>,
    val isComplete: Boolean = false
)
