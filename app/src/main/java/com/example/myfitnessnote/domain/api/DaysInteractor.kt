package com.example.myfitnessnote.domain.api

import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExercisesListResult

interface DaysInteractor {
    fun getDayList(): List<DayItem>
    fun saveDayList(dayList: List<DayItem>)
    fun getDayExercises(dayId: Int): ExercisesListResult

}