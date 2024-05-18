package com.example.myfitnessnote.presentetion.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.ExerciseItemBinding
import com.example.myfitnessnote.domain.models.DurationLabel
import com.example.myfitnessnote.domain.models.ExerciseItem

class ExercisesViewHolder(private val binding: ExerciseItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(exercise: ExerciseItem) = with(binding) {
        Glide.with(itemView.context)
            .load("$FILE_PATH$FILE_SEPARATOR${exercise.icon}")
            .placeholder(R.drawable.ic_empty)
            .centerCrop()
            .into(tvExerciseIcon)
        tvExerciseName.text = exercise.name
        tvExerciseReplay.text = if (exercise.durationLabel == DurationLabel.TIME) {
            "${exercise.duration} сек."
        } else {
            "${exercise.duration} раз"
        }
        tvExerciseIndicatorIsComplete.isChecked = exercise.isComplete
    }
}

private const val FILE_PATH = "file://"
private const val FILE_SEPARATOR = "/android_asset/"