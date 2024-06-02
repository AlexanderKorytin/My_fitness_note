package com.example.myfitnessnote.presentetion.models.days

sealed interface DaysScreenState {
    data object Loading : DaysScreenState
    data class Content(val data: DaysScreenData) : DaysScreenState
    data object Error : DaysScreenState
}