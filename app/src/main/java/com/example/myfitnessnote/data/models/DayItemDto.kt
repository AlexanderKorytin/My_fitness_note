package com.example.myfitnessnote.data.models

import com.example.myfitnessnote.domain.models.DayItem

data class DayItemDto(
    val dayId: Int,
    val exercises: String,
    val isComplete: Boolean = false
)

fun map(dayItemDto: DayItemDto): DayItem {
    return DayItem(
        dayId = dayItemDto.dayId,
        exercisesIndexes = dayItemDto.exercises.split(DELIMITER).map {
            it.toInt()
        },
        isComplete = dayItemDto.isComplete
    )
}

private const val DELIMITER = ','