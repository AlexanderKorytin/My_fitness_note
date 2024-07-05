package com.example.myfitnessnote.ui.fragments.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.FragmentTrainingBinding
import com.example.myfitnessnote.domain.models.Difficulty
import com.example.myfitnessnote.presentetion.adapters.difficulty_container.DifficultyPagerAdapter
import com.example.myfitnessnote.presentetion.models.days.DaysIntent
import com.example.myfitnessnote.presentetion.models.days.DaysScreenData
import com.example.myfitnessnote.presentetion.models.days.DaysScreenState
import com.example.myfitnessnote.presentetion.viewmodel.TrainingViewModel
import com.example.myfitnessnote.utils.BindingFragment
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
        }.attach()
        viewModel.update(DaysIntent.RequestDaysList)
        viewModel.daysScreenState.observe(viewLifecycleOwner) { result ->
            processingResult(result)
        }

        with(binding) {
            pagerContainer.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tvDifficulty.text = Difficulty.entries[position].name
                    when (position) {
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
            })
        }
    }

    private fun processingResult(result: DaysScreenState) {
        when (result) {
            is DaysScreenState.Content -> {
                showContent(result.data)
            }

            DaysScreenState.Error -> {

            }

            DaysScreenState.Loading -> {

            }
        }
    }

    private fun showContent(data: DaysScreenData) = with(binding) {
        val remainsDays = "${requireContext().getString(R.string.remains_days)} ${data.remainsDays}"
        tvTextProgress.text = remainsDays
        tvProgressExercises.setProgress(data.progress, true)
    }

}
