package com.example.myfitnessnote.presentetion.vievmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.presentetion.models.DaysIntent
import com.example.myfitnessnote.presentetion.models.DaysScreenData
import com.example.myfitnessnote.presentetion.models.DaysScreenState
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
            _daysScreenState.postValue(DaysScreenState.Content(DaysScreenData(days = list)))
        } catch (e: Exception) {
            _daysScreenState.postValue(DaysScreenState.Error)
            delay(DELAY_REQUEST_LIST)
            getDaysList()
        }
    }

    companion object {
        private const val DELAY_REQUEST_LIST = 1000L
    }
}