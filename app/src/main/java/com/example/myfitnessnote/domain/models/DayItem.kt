package com.example.myfitnessnote.domain.models

data class DayItem(
    val dayId: Int,
    val dayNumber: Int,
    val difficulty: Difficulty,
    val exercises: List<ExerciseItem>,
    val isComplete: Boolean = false
)
