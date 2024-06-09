package com.example.myfitnessnote.data.models

import com.example.myfitnessnote.domain.models.DurationLabel
import com.example.myfitnessnote.domain.models.ExerciseItem

data class ExerciseDto(
    val exerciseName: String,
    val exerciseDuration: String,
    val exerciseIcon: String,
    val isComplete: Boolean = false
)


fun map(exerciseDto: ExerciseDto): ExerciseItem {
    return ExerciseItem(
        name = exerciseDto.exerciseName,
        duration = exerciseDto.exerciseDuration.split(DURATION_DELIMITER).last().toInt(),
        durationLabel = getDurationLabel(exerciseDto.exerciseDuration.split(DURATION_DELIMITER)[0]),
        icon = exerciseDto.exerciseIcon,
        isComplete = exerciseDto.isComplete
    )
}

fun map(exercise: ExerciseItem): ExerciseDto {
    return ExerciseDto(
        exerciseName = exercise.name,
        exerciseDuration = getExerciseDuration(exercise),
        exerciseIcon = exercise.icon,
        isComplete = exercise.isComplete
    )
}

private fun getExerciseDuration(exercise: ExerciseItem): String {
    val result = StringBuilder()
    if (exercise.durationLabel == DurationLabel.TIME) {
        result.append(TIME_LABEL)
    } else {
        result.append(REPLAY_LABEL)
    }
    result.append(DURATION_DELIMITER).append(exercise.duration)
    return result.toString()
}

private fun getDurationLabel(prefix: String): DurationLabel {
    val label = when (prefix) {
        TIME_LABEL -> DurationLabel.TIME
        REPLAY_LABEL -> DurationLabel.REPLAY
        else -> {
            throw (IllegalStateException())
        }
    }
    return label
}

private const val DURATION_DELIMITER = ':'
private const val TIME_LABEL = "t"
private const val REPLAY_LABEL = "r"