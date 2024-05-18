package com.example.myfitnessnote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfitnessnote.databinding.FragmentExerciseListBinding
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.example.myfitnessnote.presentetion.adapters.ExercisesAdapter
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesIntent
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesScreenState
import com.example.myfitnessnote.presentetion.vievmodel.ExerciseViewModel
import com.example.myfitnessnote.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ExerciseListFragment : BindingFragment<FragmentExerciseListBinding>() {
    val dayId = arguments?.getInt(DAY_ID) ?: 0
    private val viewModel: ExerciseViewModel by viewModel<ExerciseViewModel> {
        parametersOf(dayId)
    }
    private val adapter = ExercisesAdapter()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseListBinding {
        return FragmentExerciseListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvExercisesList.layoutManager = LinearLayoutManager(requireContext())
            tvExercisesList.adapter = adapter
            botStart.setOnClickListener {

            }
        }
        bind()
    }

    private fun bind() {
        viewModel.update(ExercisesIntent.RequestExercises)
        viewModel.screenState.observe(viewLifecycleOwner) {
            progressResult(it)
        }
    }

    private fun progressResult(result: ExercisesScreenState) {
        when (result) {
            is ExercisesScreenState.Content -> {
                showContent(result.data)
            }

            is ExercisesScreenState.Loading -> {
                showLoading()
            }

            is ExercisesScreenState.Error -> {
                showError()
            }
        }
    }

    private fun showLoading() = with(binding) {
        tvError.isVisible = false
        botStart.isVisible = false
        tvExercisesList.isVisible = false
        progressRequest.isVisible = true
    }

    private fun showError() = with(binding) {
        tvError.isVisible = true
        botStart.isVisible = false
        tvExercisesList.isVisible = false
        progressRequest.isVisible = false
    }

    private fun showContent(data: List<ExerciseItem>) = with(binding) {
        adapter.submitList(data)
        tvError.isVisible = false
        botStart.isVisible = true
        tvExercisesList.isVisible = true
        progressRequest.isVisible = false
    }

    companion object {
        const val DAY_ID = "day_id"
    }
}