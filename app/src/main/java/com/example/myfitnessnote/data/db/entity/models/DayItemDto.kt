package com.example.myfitnessnote.data.db.entity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.Difficulty
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = DayItemDto.TABLE_NAME_DAYS_LIST)
data class DayItemDto(
    @PrimaryKey(autoGenerate = true)
    val dayId: Int? = null,
    val dayNumber: Int,
    val difficulty: Difficulty = Difficulty.EASY,
    val exercises: String,
    val isComplete: Boolean = false
) {
    companion object {
        const val TABLE_NAME_DAYS_LIST = "days_list"
    }
}

fun map(dayItemDto: DayItemDto): DayItem {
    return DayItem(
        dayId = dayItemDto.dayNumber,
        dayNumber = dayItemDto.dayNumber,
        difficulty = dayItemDto.difficulty,
        exercises = convert(dayItemDto.exercises).map { map(it) },
        isComplete = dayItemDto.isComplete
    )
}

fun map(dayItem: DayItem): DayItemDto {
    return DayItemDto(
        dayNumber = dayItem.dayId,
        exercises = convert(dayItem.exercises),
        isComplete = dayItem.isComplete
    )
}

private fun convert(exercises: String): List<ExerciseDto> {
    return Gson().fromJson<List<ExerciseDto>>(
        exercises,
        object : TypeToken<List<ExerciseDto>>() {}.type
    )
}

private fun convert(exercises: List<ExerciseItem>): String {
    val listExercises = exercises.map { map(it) }
    return Gson().toJson(listExercises, object : TypeToken<List<ExerciseDto>>() {}.type)
}