package com.example.myfitnessnote.data.storage.impl

import android.content.Context
import android.content.SharedPreferences
import com.example.myfitnessnote.R
import com.example.myfitnessnote.data.models.DayItemDto
import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class DaysExercisesSharedPrefStorage(
    private val context: Context,
    private val sharedPref: SharedPreferences,
    private val json: Gson
) : DaysExercisesStorage {
    private var isFirstStart: Boolean = true

    override fun getDayList(): List<DayItemDto> {
        isFirstStart = sharedPref.getBoolean(IS_FIRST_START, true)
        if (isFirstStart) {
            createDayList()
        }
        return getDayListFromSharedPref()
    }

    private fun getDayListFromSharedPref(): List<DayItemDto> {
        return json.fromJson(
            sharedPref.getString(DAYS, json.toJson(emptyList<DayItemDto>())),
            object : TypeToken<List<DayItemDto>>() {}.type
        )
    }

    private fun createDayList() {
        val listDayDto = context.resources.getStringArray(R.array.days_execise)
            .mapIndexed { index, item ->
                DayItemDto(index, item)
            }
        sharedPref.edit().putString(DAYS, json.toJson(listDayDto)).apply()
        sharedPref.edit().putBoolean(IS_FIRST_START, false).apply()
    }

    companion object {
        const val IS_FIRST_START = "first_start"
        const val DAYS = "days"
    }
}
