package com.example.myfitnessnote.domain.impl

import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.example.myfitnessnote.domain.models.ExercisesListResult

class DaysInteractorImpl(private val repository: DaysRepository) : DaysInteractor {
    private var listDays: List<DayItem> = emptyList()
    override fun getDayList(): List<DayItem> {
        listDays = repository.getDayList()
        return listDays
    }

    override fun getDayExercises(dayId: Int): ExercisesListResult {
        val data = repository.getAllExercises().getOrNull()
        val error = repository.getAllExercises().exceptionOrNull()
        val result = when {
            data != null -> {
                val listDayExercises = mutableListOf<ExerciseItem>()
                listDays[dayId].exercisesIndexes.forEach {
                    listDayExercises.add(data[it])
                }
                ExercisesListResult.Content(listDayExercises)
            }

            error is IllegalStateException -> {
                ExercisesListResult.ErrorState
            }


            else -> {
                ExercisesListResult.ErrorUnKnow
            }
        }
        return result
    }
}