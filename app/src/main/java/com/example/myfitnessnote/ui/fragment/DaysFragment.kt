package com.example.myfitnessnote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.FragmentDaysBinding
import com.example.myfitnessnote.domain.models.DayItem
import com.example.myfitnessnote.presentetion.adapters.DaysAdapter
import com.example.myfitnessnote.presentetion.models.DaysIntent
import com.example.myfitnessnote.presentetion.models.DaysScreenData
import com.example.myfitnessnote.presentetion.models.DaysScreenState
import com.example.myfitnessnote.presentetion.vievmodel.DaysViewModel
import com.example.myfitnessnote.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DaysFragment : BindingFragment<FragmentDaysBinding>() {

    private val daysAdapter = DaysAdapter { dayItem ->
        dayClickDebounce?.let { onClick -> onClick(dayItem) }
    }
    private var dayClickDebounce: ((DayItem) -> Unit)? = null

    private val viewModel: DaysViewModel by viewModel<DaysViewModel>()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDaysBinding {
        return FragmentDaysBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        with(binding.daysList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = daysAdapter
        }
        viewModel.update(DaysIntent.RequestDaysList)
        viewModel.daysScreenState.observe(viewLifecycleOwner) { result ->
            processingResult(result)
        }
    }

    private fun processingResult(result: DaysScreenState) {
        when (result) {
            is DaysScreenState.Error -> {
                showError()
            }

            is DaysScreenState.Loading -> {
                showLoading()
            }

            is DaysScreenState.Content -> {
                showContent(result.data)
            }
        }
    }

    private fun showLoading() = with(binding) {
        daysList.isVisible = false
        progressRequest.isVisible = true
        tvError.isVisible = false
    }

    private fun showError() = with(binding) {
        daysList.isVisible = false
        progressRequest.isVisible = false
        tvError.isVisible = true
    }

    private fun showContent(data: DaysScreenData) = with(binding) {
        val remainsDays = "${requireContext().getString(R.string.remains_days)} ${data.remainsDays}"
        daysList.isVisible = true
        progressRequest.isVisible = false
        tvError.isVisible = false
        daysAdapter.submitList(data.days)
        tvTextProgress.text = remainsDays
        progressExercises.setProgress(data.progress, true)
    }
}