package com.example.myfitnessnote.domain.models

data class DayItem(
    val dayId: Int,
    val exercisesCount: Int,
    val isComplete: Boolean = false
)
