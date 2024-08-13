package com.example.myfitnessnote.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfitnessnote.data.db.entity.models.DayItemDto
import com.example.myfitnessnote.domain.models.Difficulty
import kotlinx.coroutines.flow.Flow

@Dao
interface DaysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayItem(dayItemDto: DayItemDto)

    @Query("SELECT * FROM ${DayItemDto.TABLE_NAME_DAYS_LIST} WHERE difficulty = :difficulty")
    fun getDaysByDifficulty(difficulty: Difficulty): Flow<List<DayItemDto>>

    @Query("SELECT * FROM ${DayItemDto.TABLE_NAME_DAYS_LIST}")
    fun getDaysByDifficulty(): Flow<List<DayItemDto>>

    @Query("SELECT * FROM ${DayItemDto.TABLE_NAME_DAYS_LIST} WHERE dayId = :dayId")
    fun getDayById(dayId: Int): Flow<DayItemDto>
}
