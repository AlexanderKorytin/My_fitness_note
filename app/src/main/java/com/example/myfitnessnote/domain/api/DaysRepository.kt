package com.example.myfitnessnote.domain.api

import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExerciseItem

interface DaysRepository {
    fun getDayList(): List<DayItem>

    fun getAllExercises(): Result<List<ExerciseItem>>
}