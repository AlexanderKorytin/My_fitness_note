package com.example.myfitnessnote.presentetion.models.training

sealed interface TrainingIntent {
    data object RequestDaysList : TrainingIntent
}
