package com.example.myfitnessnote.presentetion.viewmodel

import com.example.myfitnessnote.domain.api.DaysInteractor

class TrainingViewModel(override val interactor: DaysInteractor) : DaysViewModel(interactor)