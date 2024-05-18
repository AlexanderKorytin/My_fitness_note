package com.example.myfitnessnote.presentetion.models.days

import com.example.myfitnessnote.domain.models.DayItem

data class DaysScreenData(
    val days: List<DayItem>,
    val progress: Int = 0,
    val remainsDays: Int = days.size
)
