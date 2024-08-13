package com.example.myfitnessnote.data.storage.impl

import android.content.Context
import android.content.SharedPreferences
import com.example.myfitnessnote.R
import com.example.myfitnessnote.data.db.AppDataBase
import com.example.myfitnessnote.data.db.entity.models.DayItemDto
import com.example.myfitnessnote.data.db.entity.models.ExerciseDto
import com.example.myfitnessnote.data.storage.api.DaysExercisesStorage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class DaysExercisesSharedPrefStorage(
    private val context: Context,
    private val sharedPref: SharedPreferences,
    private val json: Gson,
    private val dataBase: AppDataBase
) : DaysExercisesStorage {
    private var isFirstStart: Boolean = true

    override fun getDayList(): List<DayItemDto> {
        isFirstStart = sharedPref.getBoolean(IS_FIRST_START, true)
        if (isFirstStart) {
            createDayList()
        }
        return getDayListFromSharedPref()
    }


    override fun updateDayList(dayList: List<DayItemDto>) {
        sharedPref.edit().putString(DAYS, json.toJson(dayList)).apply()
    }

    override fun resetDayList() {
        sharedPref.edit().clear().apply()
    }

    private fun getDayListFromSharedPref(): List<DayItemDto> {
        return json.fromJson(
            sharedPref.getString(DAYS, json.toJson(emptyList<DayItemDto>())),
            object : TypeToken<List<DayItemDto>>() {}.type
        )
    }

    private fun createDayList() {
        val listDayDto = context.resources.getStringArray(R.array.days_exercises)
            .mapIndexed { index, item ->
                DayItemDto(
                    dayNumber = index,
                    exercisesIds = item,
                    exercises = getDayListExercises(item.split(LIST_EXERCISES_DELIMITER))
                )
            }
        sharedPref.edit().putString(DAYS, json.toJson(listDayDto)).apply()
        sharedPref.edit().putBoolean(IS_FIRST_START, false).apply()
    }

    private fun getDayListExercises(list: List<String>): String {
        val result = mutableListOf<ExerciseDto>()
        val listExercises = getListExercises()
        list.forEach { result.add(listExercises[it.toInt()]) }
        return json.toJson(result, object : TypeToken<List<ExerciseDto>>() {}.type)
    }

    override fun getListExercises(): List<ExerciseDto> {
        return context.resources.getStringArray(R.array.exercise).map {
            val currentExercise = it.split(EXERCISE_DELIMITER)
            ExerciseDto(
                exerciseName = currentExercise[0],
                exerciseDuration = currentExercise[1],
                subTitle = currentExercise[0], // времянка
                exerciseIcon = currentExercise[2],
                kCal = 0 // времянка
            )
        }
    }

    companion object {
        const val IS_FIRST_START = "first_start"
        const val DAYS = "days"
        const val EXERCISE_DELIMITER = '|'
        const val LIST_EXERCISES_DELIMITER = ','
    }
}
