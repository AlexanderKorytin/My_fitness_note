package com.example.myfitnessnote.ui.fragments.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.FragmentTrainingBinding
import com.example.myfitnessnote.domain.models.Difficulty
import com.example.myfitnessnote.presentetion.adapters.difficulty_container.DifficultyPagerAdapter
import com.example.myfitnessnote.presentetion.models.training.TrainingIntent
import com.example.myfitnessnote.presentetion.models.training.TrainingScreenData
import com.example.myfitnessnote.presentetion.models.training.TrainingScreenState
import com.example.myfitnessnote.presentetion.viewmodel.TrainingViewModel
import com.example.myfitnessnote.utils.BindingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrainingFragment : BindingFragment<FragmentTrainingBinding>() {

    private val viewModel: TrainingViewModel by viewModel<TrainingViewModel>()

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
            binding.tvDifficulty.text = Difficulty.entries[0].name
            binding.tvImage.setImageResource(R.drawable.easy)
        }.attach()
        viewModel.update(TrainingIntent.RequestDaysList)
        viewModel.trainingScreenState.observe(viewLifecycleOwner) { result ->
            processingResult(result)
        }

        with(binding) {
            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.tvDifficulty.text = tab?.text
                    when (binding.tabLayout.selectedTabPosition) {
                        0 -> {
                            tvImage.setImageResource(R.drawable.easy)
                        }

                        1 -> {
                            tvImage.setImageResource(R.drawable.normal)
                        }

                        2 -> {
                            tvImage.setImageResource(R.drawable.hard)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }
    }

    private fun processingResult(result: TrainingScreenState) {
        when (result) {
            is TrainingScreenState.Content -> {
                showContent(result.data)
            }

            TrainingScreenState.Error -> {

            }
        }
    }

    private fun showContent(data: TrainingScreenData) = with(binding) {
        val remainsDays = "${requireContext().getString(R.string.remains_days)} ${data.remainsDays}"
        tvTextProgress.text = remainsDays
        tvProgressExercises.setProgress(data.progress, true)
    }

}
