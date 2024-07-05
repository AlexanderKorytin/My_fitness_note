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

open class DaysViewModel(protected open val interactor: DaysInteractor) : ViewModel() {

    protected val _daysScreenState: MediatorLiveData<DaysScreenState> =
        MediatorLiveData(DaysScreenState.Loading)

    val daysScreenState: LiveData<DaysScreenState> = _daysScreenState
    fun update(intent: DaysIntent) {
        when (intent) {
            is DaysIntent.RequestDaysList -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getDaysList()
                }
            }

            DaysIntent.Reset -> {
                viewModelScope.launch(Dispatchers.IO) {
                    reset()
                    getDaysList()
                }
            }
        }
    }

    protected suspend fun getDaysList() {
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

    protected fun getRemainsDays(list: List<DayItem>): Int {
        var counter = list.size
        list.forEach { if (it.isComplete) counter-- }
        return counter
    }

    protected fun getProgress(list: List<DayItem>): Int {
        var progress = 0
        list.forEach { if (it.isComplete) progress++ }
        return (progress.toDouble() / list.size.toDouble()).toPercent()
    }

    protected fun reset() {
        interactor.resetDayList()
    }

    protected fun Double.toPercent(): Int {
        return (this * TO_PERCENT).toInt()
    }

    companion object {
        private const val DELAY_REQUEST_LIST = 1000L
        private const val TO_PERCENT = 100
    }
}