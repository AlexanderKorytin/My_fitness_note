package com.example.myfitnessnote.data.storage.api

import com.example.myfitnessnote.data.db.entity.models.DayItemDto
import com.example.myfitnessnote.data.db.entity.models.ExerciseDto

interface DaysExercisesStorage {
    fun getDayList(): List<DayItemDto>
    fun getListExercises(): List<ExerciseDto>
    fun updateDayList(dayList: List<DayItemDto>)
    fun resetDayList()
}