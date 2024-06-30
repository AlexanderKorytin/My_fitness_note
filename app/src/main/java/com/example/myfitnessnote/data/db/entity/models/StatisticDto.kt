package com.example.myfitnessnote.data.db.entity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfitnessnote.domain.models.StatisticItem

@Entity(tableName = StatisticDto.TABLE_NAME_STATISTIC)
data class StatisticDto(
    @PrimaryKey
    val data: String,
    val kCal: Int,
    val duration: String
) {
    companion object {
        const val TABLE_NAME_STATISTIC = "statistic_table"
    }
}

fun map (statisticDto: StatisticDto): StatisticItem {
    return StatisticItem(
        data = statisticDto.data,
        duration = statisticDto.duration,
        kCal = statisticDto.kCal
    )
}

fun map (statisticItem: StatisticItem): StatisticDto {
    return StatisticDto(
        data = statisticItem.data,
        duration = statisticItem.duration,
        kCal = statisticItem.kCal
    )
}