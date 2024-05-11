package com.example.myfitnessnote.data.storage.impl

import android.content.Context
import com.example.myfitnessnote.R
import com.example.myfitnessnote.data.models.DayItemDto
import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage

data class DaysExercisesStorageImpl(private val context: Context) : DaysExercisesStorage {
    override fun getDayList(): List<DayItemDto> {
        return context.resources.getStringArray(R.array.days_execise).mapIndexed { index, item ->
            DayItemDto(index, item)
        }
    }
}
