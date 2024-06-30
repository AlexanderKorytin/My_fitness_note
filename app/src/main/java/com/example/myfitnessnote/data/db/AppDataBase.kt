package com.example.myfitnessnote.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfitnessnote.data.db.dao.DaysDao
import com.example.myfitnessnote.data.db.dao.ExerciseDao
import com.example.myfitnessnote.data.db.dao.StatisticDao
import com.example.myfitnessnote.data.db.dao.WeightDao
import com.example.myfitnessnote.data.db.entity.models.DayItemDto
import com.example.myfitnessnote.data.db.entity.models.ExerciseDto
import com.example.myfitnessnote.data.db.entity.models.StatisticDto
import com.example.myfitnessnote.data.db.entity.models.WeightDto

@Database(
    version = VERSION,
    entities = [
        DayItemDto::class,
        ExerciseDto::class,
        WeightDto::class,
        StatisticDto::class
    ]
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDaysDao(): DaysDao
    abstract fun getExerciseDao(): ExerciseDao
    abstract fun getWeightDASo(): WeightDao
    abstract fun getStatisticDao(): StatisticDao
}

private const val VERSION = 1