package com.example.myfitnessnote.data.repository

import com.example.myfitnessnote.data.models.map
import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.models.DayItem

class DaysRepositoryImpl(private val storage: DaysExercisesStorage) : DaysRepository {
    override fun getDayList(): List<DayItem> {
        return storage.getDayList().map {
            map(it)
        }
    }

}