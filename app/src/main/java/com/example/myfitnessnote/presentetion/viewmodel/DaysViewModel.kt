package com.example.myfitnessnote.presentetion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.presentetion.models.days.DaysIntent
import com.example.myfitnessnote.presentetion.models.days.DaysScreenData
import com.example.myfitnessnote.presentetion.models.days.DaysScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DaysViewModel(private val interactor: DaysInteractor) : ViewModel() {

    private val _daysScreenState: MediatorLiveData<DaysScreenState> =
        MediatorLiveData(DaysScreenState.Loading)

    val daysScreenState: LiveData<DaysScreenState> = _daysScreenState
    fun update(intent: DaysIntent) {
        when (intent) {
            is DaysIntent.RequestDaysList -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getDaysList()
                }
            }
        }
    }

    private suspend fun getDaysList() {
        try {
            val list = interactor.getDayList()
            _daysScreenState.postValue(
                DaysScreenState.Content(
                    DaysScreenData(
                        days = list,
                        remainsDays = getRemainsDays(list),
                        progress = getProgress(list)
                    )
                )
            )
        } catch (e: Exception) {
            _daysScreenState.postValue(DaysScreenState.Error)
            delay(DELAY_REQUEST_LIST)
            getDaysList()
        }
    }

    private fun getRemainsDays(list: List<DayItem>): Int {
        var counter = list.size
        list.forEach { if (it.isComplete) counter-- }
        return counter
    }

    private fun getProgress(list: List<DayItem>): Int {
        var progress = 0
        list.forEach { if (it.isComplete) progress++ }
        return (progress.toDouble() / list.size.toDouble()).toPercent()
    }

    companion object {
        private const val DELAY_REQUEST_LIST = 1000L
    }
}

private fun Double.toPercent(): Int {
    return (this * TO_PERCENT).toInt()
}

private const val TO_PERCENT = 100