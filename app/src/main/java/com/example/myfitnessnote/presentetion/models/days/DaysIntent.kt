package com.example.myfitnessnote.presentetion.models.days

sealed interface DaysIntent {
    data object RequestDaysList : DaysIntent
    data object Reset : DaysIntent
}