package com.example.myfitnessnote.domain.api

import com.example.myfitnessnote.domain.models.DayItem

interface DaysInteractor {
    fun getDayList(): List<DayItem>

}