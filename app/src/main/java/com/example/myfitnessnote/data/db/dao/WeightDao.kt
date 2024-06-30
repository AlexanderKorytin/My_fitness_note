package com.example.myfitnessnote.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfitnessnote.data.db.entity.models.WeightDto
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {
    @Query("SELECT * FROM ${WeightDto.TABLE_NAME_WEIGHT} WHERE month = :month AND year = :year")
    fun getWeightByDate(month: Int, year: Int): Flow<List<WeightDto>>

    @Query("SELECT * FROM ${WeightDto.TABLE_NAME_WEIGHT} WHERE month = :month AND year = :year AND day =:day")
    suspend fun getWeightByDay(month: Int, year: Int, day: Int): WeightDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeight(weightDto: WeightDto)
}