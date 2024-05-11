package com.example.myfitnessnote.presentetion.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.DayItemBinding
import com.example.myfitnessnote.domain.models.DayItem

class DaysViewHolder(private val rootBinding: DayItemBinding, private val context: Context) : RecyclerView.ViewHolder(rootBinding.root){
    fun bind(dayItem: DayItem){
        val dayName = "${context.getString(R.string.day)} ${dayItem.dayId+1}"
        val exerciseCounter = "${dayItem.exercisesCount} ${context.getString(R.string.exercite)}"
        rootBinding.tvDayCounter.text = exerciseCounter
        rootBinding.tvDayName.text = dayName
    }
}