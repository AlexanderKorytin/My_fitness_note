package com.example.myfitnessnote.domain.models

import androidx.room.PrimaryKey

data class WeightItem(
    val weightValue: Int,
    val day: Int,
    val month: Int,
    val year: Int
)
