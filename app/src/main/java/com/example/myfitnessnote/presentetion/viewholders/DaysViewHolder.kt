package com.example.myfitnessnote.presentetion.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessnote.databinding.DayItemBinding
import com.example.myfitnessnote.domain.models.DayItem

class DaysViewHolder(private val rootBinding: DayItemBinding) : RecyclerView.ViewHolder(rootBinding.root){
    fun bind(dayItem: DayItem){
        val dayName = "День ${dayItem.dayId}"
        val exerciseCounter = "${dayItem.exercisesCount} упражнений"
        rootBinding.tvDayCounter.text = exerciseCounter
        rootBinding.tvDayName.text = dayName
    }
}