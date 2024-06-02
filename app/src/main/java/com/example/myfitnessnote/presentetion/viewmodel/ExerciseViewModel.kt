package com.example.myfitnessnote.presentetion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.example.myfitnessnote.domain.models.ExercisesListResult
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesIntent
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(private val interactor: DaysInteractor, private val dayId: Int) :
    ViewModel() {
    private var _screenState: MediatorLiveData<ExercisesScreenState> = MediatorLiveData()
    val screenState: LiveData<ExercisesScreenState> = _screenState
    private var counter = COUNTER_START
    private var exerciseList: List<ExerciseItem> = emptyList()

    fun update(intent: ExercisesIntent) {
        when (intent) {
            is ExercisesIntent.RequestExercises -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getDaysList()
                }
            }

            is ExercisesIntent.UpdateCounter -> {
                updateCounter()
            }

        }
    }

    private fun updateCounter() {
        counter++
        _screenState.postValue(ExercisesScreenState.Content(exerciseList, counter))
    }

    private fun getDaysList() {
        when (val result = interactor.getDayExercises(dayId)) {
            is ExercisesListResult.Content -> {
                exerciseList = result.data
                _screenState.postValue(
                    ExercisesScreenState.Content(
                        data = result.data,
                        counter = counter
                    )
                )
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

private const val COUNTER_START = 0