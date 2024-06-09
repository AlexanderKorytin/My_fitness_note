package com.example.myfitnessnote.presentetion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myfitnessnote.databinding.DayItemBinding
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.presentetion.viewholders.DaysViewHolder

class DaysAdapter(private val onDayClick: (day: DayItem) -> Unit) :
    ListAdapter<DayItem, DaysViewHolder>(DayItemDiffUtill()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val viewBinding = DayItemBinding.inflate(layoutInspector, parent, false)
        return DaysViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            onDayClick(currentList[holder.adapterPosition])
        }
    }
}


class DayItemDiffUtill : DiffUtil.ItemCallback<DayItem>() {
    override fun areItemsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
        return oldItem.dayId == newItem.dayId
    }

    override fun areContentsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
        return oldItem.dayId == newItem.dayId &&
                oldItem.isComplete == newItem.isComplete &&
                oldItem.exercises.size == newItem.exercises.size
    }

}