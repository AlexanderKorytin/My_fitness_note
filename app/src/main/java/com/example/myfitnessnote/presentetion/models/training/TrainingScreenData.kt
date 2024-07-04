package com.example.myfitnessnote.presentetion.models.training

import com.example.myfitnessnote.domain.models.DayItem

data class TrainingScreenData(
    val days: List<DayItem>,
    val progress: Int = 0,
    val remainsDays: Int = days.size
)
