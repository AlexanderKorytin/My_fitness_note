package com.example.myfitnessnote.data.db.entity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfitnessnote.domain.models.DurationLabel
import com.example.myfitnessnote.domain.models.ExerciseItem

@Entity(tableName = ExerciseDto.TABLE_MAME_EXERCISES)
data class ExerciseDto(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int? = null,
    val exerciseName: String,
    val subTitle: String,
    val exerciseDuration: String,
    val exerciseIcon: String,
    val isComplete: Boolean = false,
    val kCal: Int
) {
    companion object {
        const val TABLE_MAME_EXERCISES = "exercises_table"
    }
}


fun map(exerciseDto: ExerciseDto): ExerciseItem {
    return ExerciseItem(
        exerciseId = exerciseDto.exerciseId ?: DEFAULT_EX_ID,
        name = exerciseDto.exerciseName,
        subTitle = exerciseDto.subTitle,
        duration = exerciseDto.exerciseDuration.split(DURATION_DELIMITER).last().toInt(),
        durationLabel = getDurationLabel(exerciseDto.exerciseDuration.split(DURATION_DELIMITER)[0]),
        icon = exerciseDto.exerciseIcon,
        isComplete = exerciseDto.isComplete,
        kCal = exerciseDto.kCal
    )
}

fun map(exercise: ExerciseItem): ExerciseDto {
    return ExerciseDto(
        exerciseId = exercise.exerciseId,
        exerciseName = exercise.name,
        subTitle = exercise.subTitle,
        exerciseDuration = getExerciseDuration(exercise),
        exerciseIcon = exercise.icon,
        isComplete = exercise.isComplete,
        kCal = exercise.kCal
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
private const val DEFAULT_EX_ID = 0