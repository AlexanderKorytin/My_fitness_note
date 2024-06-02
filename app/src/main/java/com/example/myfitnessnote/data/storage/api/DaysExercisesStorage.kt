package com.example.myfitnessnote.data.storage.api

import com.example.myfitnessnote.data.models.DayItemDto
import com.example.myfitnessnote.data.models.ExerciseDto

interface DaysExercisesStorage {
    fun getDayList(): List<DayItemDto>

    fun getListExercises(): List<ExerciseDto>

    fun updateDayList(dayList: List<DayItemDto>)
}