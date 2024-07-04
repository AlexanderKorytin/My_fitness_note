package com.example.myfitnessnote.ui.fragments.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfitnessnote.databinding.FragmentTrainingBinding
import com.example.myfitnessnote.domain.models.Difficulty
import com.example.myfitnessnote.presentetion.adapters.difficulty_container.DifficultyPagerAdapter
import com.example.myfitnessnote.utils.BindingFragment
import com.google.android.material.tabs.TabLayoutMediator


class TrainingFragment : BindingFragment<FragmentTrainingBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTrainingBinding {
        return FragmentTrainingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        binding.pagerContainer.adapter = DifficultyPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pagerContainer) { tab, position ->
            tab.text = Difficulty.entries[position].name
        }.attach()
    }
}
