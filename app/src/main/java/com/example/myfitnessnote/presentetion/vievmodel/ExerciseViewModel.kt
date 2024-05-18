package com.example.myfitnessnote.presentetion.vievmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.models.ExercisesListResult
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesIntent
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(private val interactor: DaysInteractor, private val dayId: Int) :
    ViewModel() {
    private var _screenState: MediatorLiveData<ExercisesScreenState> = MediatorLiveData()
    val screenState: LiveData<ExercisesScreenState> = _screenState

    fun update(intent: ExercisesIntent) {
        when (intent) {
            is ExercisesIntent.RequestExercises -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getDaysList()
                }
            }
        }
    }

    private suspend fun getDaysList() {
        when (val list = interactor.getDayExercises(dayId)) {
            is ExercisesListResult.Content -> {
                _screenState.postValue(ExercisesScreenState.Content(list.data))
            }

            ExercisesListResult.ErrorState -> {
                _screenState.postValue(ExercisesScreenState.Error)
            }

            ExercisesListResult.ErrorUnKnow -> {
                _screenState.postValue(ExercisesScreenState.Error)
            }
        }
    }
}