package com.example.myfitnessnote.presentetion.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.DayItemBinding
import com.example.myfitnessnote.domain.models.DayItem

class DaysViewHolder(private val rootBinding: DayItemBinding) :
    RecyclerView.ViewHolder(rootBinding.root) {
    fun bind(dayItem: DayItem) = with(rootBinding) {
        var completedExercises = 0
        dayItem.exercises.forEach { if (it.isComplete) completedExercises++ }
        val dayName = "${root.context.getString(R.string.day)} ${dayItem.dayId + 1}"
        val exerciseCounter =
            "${dayItem.exercises.size} ${root.context.getString(R.string.exercise)} / ${completedExercises} выполнено"
        tvDayCounter.text = exerciseCounter
        tvDayName.text = dayName
        tvIcComplete.setImageResource(getIconComplete(dayItem.isComplete))
    }

    private fun getIconComplete(isComplete: Boolean): Int {
        if (isComplete) {
            rootBinding.tvIcComplete.setColorFilter(itemView.context.getColor(R.color.orange))
            return R.drawable.ic_complete
        } else {
            rootBinding.tvIcComplete.setColorFilter(itemView.context.getColor(R.color.dark_gray))
            return R.drawable.ic_not_complete
        }
    }
}