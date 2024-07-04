package com.example.myfitnessnote.presentetion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.presentetion.models.training.TrainingIntent
import com.example.myfitnessnote.presentetion.models.training.TrainingScreenData
import com.example.myfitnessnote.presentetion.models.training.TrainingScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TrainingViewModel(private val interactor: DaysInteractor) : ViewModel() {
    private val _trainingScreenState: MutableLiveData<TrainingScreenState> = MutableLiveData()
    val trainingScreenState: LiveData<TrainingScreenState> = _trainingScreenState
    fun update(intent: TrainingIntent) {
        when (intent) {
            is TrainingIntent.RequestDaysList -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getDaysList()
                }
            }
        }
    }

    private suspend fun getDaysList() {
        try {
            val list = interactor.getDayList()
            _trainingScreenState.postValue(
                TrainingScreenState.Content(
                    TrainingScreenData(
                        days = list,
                        remainsDays = getRemainsDays(list),
                        progress = getProgress(list)
                    )
                )
            )
        } catch (e: Exception) {
            _trainingScreenState.postValue(TrainingScreenState.Error)
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