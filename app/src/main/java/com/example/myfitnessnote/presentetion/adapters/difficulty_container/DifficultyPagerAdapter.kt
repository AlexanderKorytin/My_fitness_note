package com.example.myfitnessnote.presentetion.adapters.difficulty_container

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfitnessnote.domain.models.Difficulty
import com.example.myfitnessnote.ui.fragments.days.DaysFragment

class DifficultyPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return Difficulty.entries.size
    }

    override fun createFragment(position: Int): Fragment {
        return DaysFragment.newInstance(Difficulty.entries[position])
    }
}

private const val VIEW_PAGER_COUNT = 3