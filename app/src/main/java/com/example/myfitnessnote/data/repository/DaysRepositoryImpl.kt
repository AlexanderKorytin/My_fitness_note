package com.example.myfitnessnote.data.repository

import com.example.myfitnessnote.data.db.entity.models.map
import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.domain.models.ExerciseItem

class DaysRepositoryImpl(private val storage: DaysExercisesStorage) : DaysRepository {
    override fun getDayList(): List<DayItem> {
        return storage.getDayList().map {
            map(it)
        }
    }

    override fun saveDayList(dayList: List<DayItem>) {
        storage.updateDayList(dayList = dayList.map { map(it) })
    }

    override fun getAllExercises(): Result<List<ExerciseItem>> {
        val result = try {
            Result.success(storage.getListExercises().map {
                map(it)
            })
        } catch (e: IllegalStateException) {
            Result.failure(e)
        }
        return result
    }

    override fun resetDayList() {
        storage.resetDayList()
    }

}