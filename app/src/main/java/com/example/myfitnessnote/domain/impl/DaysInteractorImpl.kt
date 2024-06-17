package com.example.myfitnessnote.domain.impl

import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExerciseItem

class DaysInteractorImpl(private val repository: DaysRepository) : DaysInteractor {
    override fun getDayList(): List<DayItem> {
        return repository.getDayList()
    }

    override fun saveDayList(dayList: List<DayItem>) {
        repository.saveDayList(dayList)
    }

    override fun saveDayExercisesList(exercises: List<ExerciseItem>, dayId: Int) {
        val listDays = getDayList()
        val result = mutableListOf<DayItem>()
        listDays.forEach {
            if (it.dayId == dayId) {
                result.add(it.copy(exercises = exercises))
            } else {
                result.add(it)
            }
        }
        saveDayList(result)
    }

    override fun getDayExercises(dayId: Int): List<ExerciseItem> {
        val listDays = getDayList()
        return listDays[dayId].exercises
    }

    override fun repeatDayExercise(dayId: Int) {
        val listDays = getDayList()
        val newDayList = mutableListOf<DayItem>()
        val newExercises = mutableListOf<ExerciseItem>()
        listDays.forEach { dayItem ->
            if (dayItem.dayId == dayId) {
                dayItem.exercises.forEach { exerciseItem ->
                    newExercises.add(exerciseItem.copy(isComplete = false))
                }
                newDayList.add(dayItem.copy(isComplete = false, exercises = newExercises))
            } else {
                newDayList.add(dayItem)
            }
        }
        saveDayList(newDayList)
    }

    override fun resetDayList() {
        repository.resetDayList()
    }
}