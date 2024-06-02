package com.example.myfitnessnote.presentetion.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myfitnessnote.domain.api.DaysInteractor

class DayFinalViewModel(private val interactor: DaysInteractor, private val dayId: Int) :
    ViewModel() {

    fun saveDaysList() {
        val daysList = interactor.getDayList()
            .map { if (it.dayId == dayId) it.copy(isComplete = COMPLETE) else it }
        interactor.saveDayList(daysList)
    }
}

private const val COMPLETE = true
