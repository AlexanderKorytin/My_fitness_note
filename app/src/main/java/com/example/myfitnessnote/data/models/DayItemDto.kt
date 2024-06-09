package com.example.myfitnessnote.data.models

import com.example.myfitnessnote.domain.models.DayItem

data class DayItemDto(
    val dayId: Int,
    val exercises: List<ExerciseDto>,
    val isComplete: Boolean = false
)

fun map(dayItemDto: DayItemDto): DayItem {
    return DayItem(
        dayId = dayItemDto.dayId,
        exercises =  dayItemDto.exercises.map { map(it) },
        isComplete = dayItemDto.isComplete
    )
}

fun map(dayItem: DayItem): DayItemDto {
    return DayItemDto(
        dayId = dayItem.dayId,
        exercises = dayItem.exercises.map { map(it) },
        isComplete = dayItem.isComplete
    )
}
