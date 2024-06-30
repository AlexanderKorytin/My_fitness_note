package com.example.myfitnessnote.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfitnessnote.data.db.entity.models.StatisticDto

@Dao
interface StatisticDao {
    @Query("SELECT * FROM ${StatisticDto.TABLE_NAME_STATISTIC}")
    suspend fun getStatistic(): List<StatisticDto>

    @Query("SELECT * FROM ${StatisticDto.TABLE_NAME_STATISTIC} WHERE data = :date")
    suspend fun getStatisticByDate(date: String): StatisticDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayStatistic(dayStatistic: StatisticDto)
}