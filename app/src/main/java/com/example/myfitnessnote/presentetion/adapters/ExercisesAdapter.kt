package com.example.myfitnessnote.presentetion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myfitnessnote.databinding.ExerciseItemBinding
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.example.myfitnessnote.presentetion.viewholders.ExercisesViewHolder

class ExercisesAdapter : ListAdapter<ExerciseItem, ExercisesViewHolder>(ExerciseDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val viewBinding = ExerciseItemBinding.inflate(layoutInspector, parent, false)
        return ExercisesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

class ExerciseDiffUtil : DiffUtil.ItemCallback<ExerciseItem>() {
    override fun areItemsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.duration == newItem.duration &&
                oldItem.icon == newItem.icon &&
                oldItem.isComplete == newItem.isComplete
    }

}