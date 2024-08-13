package com.example.myfitnessnote.data.db.entity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfitnessnote.domain.models.WeightItem

@Entity(tableName = WeightDto.TABLE_NAME_WEIGHT)
data class WeightDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val weightValue: Int,
    val day: Int,
    val month: Int,
    val year: Int
) {
    companion object {
        const val TABLE_NAME_WEIGHT = "weight_table"
    }
}

fun map(weightDto: WeightDto): WeightItem {
    return WeightItem(
        weightValue = weightDto.weightValue,
        day = weightDto.day,
        month = weightDto.month,
        year = weightDto.year
    )
}

fun map(weightItem: WeightItem): WeightDto {
    return WeightDto(
        weightValue = weightItem.weightValue,
        day = weightItem.day,
        month = weightItem.month,
        year = weightItem.year
    )
}