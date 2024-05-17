package com.example.myfitnessnote.data.models

import com.example.myfitnessnote.domain.models.DurationLabel
import com.example.myfitnessnote.domain.models.ExerciseItem

data class ExerciseDto(
    val exerciseName: String,
    val exerciseDuration: String,
    val exerciseIcon: String
)


fun map(exerciseDto: ExerciseDto): ExerciseItem {
    return ExerciseItem(
        name = exerciseDto.exerciseName,
        duration = exerciseDto.exerciseDuration.split(DURATION_DELIMITER)[1].toInt(),
        durationLabel = getDurationLabel(exerciseDto.exerciseDuration.split(DURATION_DELIMITER)[0]),
        icon = exerciseDto.exerciseIcon
    )
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