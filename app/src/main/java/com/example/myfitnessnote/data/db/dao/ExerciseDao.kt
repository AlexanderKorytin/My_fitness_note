package com.example.myfitnessnote.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myfitnessnote.data.db.entity.models.ExerciseDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM ${ExerciseDto.TABLE_MAME_EXERCISES}")
    suspend fun getAllExercises(): List<ExerciseDto>
}