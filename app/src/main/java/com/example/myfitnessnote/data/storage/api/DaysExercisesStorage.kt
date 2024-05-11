package com.example.myfitnessnote.data.storage.api

import com.example.myfitnessnote.data.models.DayItemDto

interface DaysExercisesStorage {
    fun getDayList(): List<DayItemDto>
}