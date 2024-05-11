package com.example.myfitnessnote.presentetion.models

import androidx.annotation.StringRes
import com.example.myfitnessnote.domain.models.DayItem

sealed interface DaysScreenState {
    data object Loading: DaysScreenState
    data class Content(val data: List<DayItem>) : DaysScreenState
    data object Error : DaysScreenState
}