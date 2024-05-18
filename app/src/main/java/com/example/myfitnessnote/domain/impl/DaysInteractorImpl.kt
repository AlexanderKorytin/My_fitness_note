package com.example.myfitnessnote.domain.impl

import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.example.myfitnessnote.domain.models.ExercisesListResult
import java.lang.IndexOutOfBoundsException

class DaysInteractorImpl(private val repository: DaysRepository) : DaysInteractor {
    override fun getDayList(): List<DayItem> {
       return repository.getDayList()
    }

    override fun getDayExercises(dayId: Int): ExercisesListResult {
        val listDays = getDayList()
        val data = repository.getAllExercises().getOrNull()
        val error = repository.getAllExercises().exceptionOrNull()
        val result = when {
            data != null -> {
                val listDayExercises = mutableListOf<ExerciseItem>()
                listDays[dayId].exercisesIndexes.forEach {
                    val currentExercise = try {
                        data[it]
                    } catch (e: IndexOutOfBoundsException) {
                        data[0]
                    }
                    listDayExercises.add(currentExercise)
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