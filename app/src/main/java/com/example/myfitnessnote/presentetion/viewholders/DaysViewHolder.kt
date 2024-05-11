package com.example.myfitnessnote.presentetion.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.DayItemBinding
import com.example.myfitnessnote.domain.models.DayItem

class DaysViewHolder(private val rootBinding: DayItemBinding) : RecyclerView.ViewHolder(rootBinding.root){
    fun bind(dayItem: DayItem) = with(rootBinding){
        val dayName = "${root.context.getString(R.string.day)} ${dayItem.dayId+1}"
        val exerciseCounter = "${dayItem.exercisesCount} ${root.context.getString(R.string.exercite)}"
        tvDayCounter.text = exerciseCounter
        tvDayName.text = dayName
    }
}