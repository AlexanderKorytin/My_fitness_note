package com.example.myfitnessnote.domain.models

data class DayItem(
    val dayId: Int,
    val exercisesIndexes: List<Int>,
    val isComplete: Boolean = false
)
