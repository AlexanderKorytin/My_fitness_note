package com.example.myfitnessnote.utils

import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

fun <T> debounce(
    delayMillis: Long,
    coroutineScope: CoroutineScope,
    useLastParam: Boolean,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (useLastParam) {
            debounceJob?.cancel()
        }
        if (debounceJob?.isCompleted != false || useLastParam) {
            debounceJob = coroutineScope.launch {
                delay(delayMillis)
                action(param)
            }
        }
    }
}

suspend fun showExerciseProgress(
    time: Int,
    scope: CoroutineScope,
    tvCounter: TextView,
    tvProcess: ProgressBar,
    action: () -> Unit
) {
    tvProcess.max = time
    var progressCurrentValue =
        time
    val processStep = PROGRESS_DELAY.toInt()
    scope.launch(Dispatchers.IO) {
        val counterJob = scope.launch(Dispatchers.Main) {
            for (i in time downTo 0 step COUNTER_STEP) {
                tvCounter.text = SimpleDateFormat("mm:ss", java.util.Locale.getDefault()).format(i)
                delay(COUNTER_DELAY)
            }
        }
        val progressJob = scope.launch(Dispatchers.Main) {
            while (progressCurrentValue >= 0) {
                delay(PROGRESS_DELAY)
                progressCurrentValue -= processStep
                tvProcess.progress = progressCurrentValue
            }
        }
        progressJob.join()
        withContext(Dispatchers.Main) {
            action.invoke()
        }
    }
}

private const val COUNTER_DELAY = 1000L
private const val PROGRESS_DELAY = 100L
private const val COUNTER_STEP = 1000