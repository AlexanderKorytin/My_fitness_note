package com.example.myfitnessnote.domain.api

import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExerciseItem

interface DaysInteractor {
    fun getDayList(): List<DayItem>
    fun saveDayList(dayList: List<DayItem>)
    fun saveDayExercisesList(exercises: List<ExerciseItem>, dayId: Int)
    fun getDayExercises(dayId: Int): List<ExerciseItem>
    fun repeatDayExercise(dayId: Int)

}