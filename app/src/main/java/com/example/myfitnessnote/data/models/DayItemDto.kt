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

fun map(dayItem: DayItem): DayItemDto {
    return DayItemDto(
        dayId = dayItem.dayId,
        exercises = getExercises(dayItem.exercisesIndexes),
        isComplete = dayItem.isComplete
    )
}

private fun getExercises(list: List<Int>): String {
    val str = buildString {
        list.forEachIndexed { index, item ->
            append(item)
            if (index < list.size - 1) {
                append(DELIMITER)
            }
        }
    }
    return str
}

private const val DELIMITER = ','