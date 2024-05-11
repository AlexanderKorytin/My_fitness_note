package com.example.myfitnessnote.domain.impl

import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.models.DayItem

class DaysInteractorImpl(private val repository: DaysRepository) : DaysInteractor {
    override fun getDayList(): List<DayItem> {
        return repository.getDayList()
    }
}