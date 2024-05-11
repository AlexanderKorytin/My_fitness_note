package com.example.myfitnessnote.presentetion.models

import com.example.myfitnessnote.domain.models.DayItem

sealed interface DaysIntent {
    data object RequestDaysList: DaysIntent
}