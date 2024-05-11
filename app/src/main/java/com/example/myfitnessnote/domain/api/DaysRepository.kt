package com.example.myfitnessnote.domain.api

import com.example.myfitnessnote.domain.models.DayItem

interface DaysRepository {
    fun getDayList(): List<DayItem>
}