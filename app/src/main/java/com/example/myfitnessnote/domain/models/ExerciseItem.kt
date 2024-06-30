package com.example.myfitnessnote.domain.models

data class ExerciseItem(
    val exerciseId: Int,
    val name: String,
    val subTitle: String,
    val duration: Int,
    val durationLabel: DurationLabel,
    val icon: String,
    val isComplete: Boolean,
    val kCal: Int
)
