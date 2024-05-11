package com.example.myfitnessnote.presentetion.models

import androidx.annotation.StringRes
import com.example.myfitnessnote.domain.models.DayItem

sealed interface DaysScreenState {
    data object Loading: DaysScreenState
    data class Content(val data: DaysScreenData) : DaysScreenState
    data object Error : DaysScreenState
}