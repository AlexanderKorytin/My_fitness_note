package com.example.myfitnessnote.domain.models

data class ExerciseItem(
    val name: String,
    val duration: Int,
    val durationLabel: DurationLabel,
    val icon: String,
    val isComplete: Boolean = false
)
