package com.example.myfitnessnote.data.repository

import com.example.myfitnessnote.data.models.map
import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExerciseItem
import java.lang.IllegalStateException

class DaysRepositoryImpl(private val storage: DaysExercisesStorage) : DaysRepository {
    override fun getDayList(): List<DayItem> {
        return storage.getDayList().map {
            map(it)
        }
    }

    override fun getAllExercises(): Result<List<ExerciseItem>> {
        val result = try {
            Result.success(storage.getListExercises().map {
                map(it)
            })
        } catch (e: IllegalStateException){
            Result.failure(e)
        }
        return result
    }
}