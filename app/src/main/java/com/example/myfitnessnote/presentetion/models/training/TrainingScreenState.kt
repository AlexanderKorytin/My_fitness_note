package com.example.myfitnessnote.presentetion.models.training

sealed interface TrainingScreenState {
    data class Content(val data: TrainingScreenData) : TrainingScreenState
    data object Error: TrainingScreenState
}