package com.example.myfitnessnote.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.FragmentExerciseListBinding
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.example.myfitnessnote.presentetion.adapters.ExercisesAdapter
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesIntent
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesScreenState
import com.example.myfitnessnote.presentetion.viewmodel.ExerciseViewModel
import com.example.myfitnessnote.utils.BindingFragment
import com.example.myfitnessnote.utils.DAY_ID
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ExerciseListFragment : BindingFragment<FragmentExerciseListBinding>() {
    private var dayId = 0
    private val viewModel: ExerciseViewModel by viewModel<ExerciseViewModel> {
        parametersOf(arguments?.getInt(DAY_ID) ?: 0)
    }
    private val adapter = ExercisesAdapter()
    private val listExercises: ArrayList<ExerciseItem> = arrayListOf()
    private var isDialogShow = false

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseListBinding {
        return FragmentExerciseListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayId = requireArguments().getInt(DAY_ID)
        with(binding) {
            tvExercisesList.layoutManager = LinearLayoutManager(requireContext())
            tvExercisesList.adapter = adapter
            botStart.setOnClickListener {
                navigateToWaitingFragment(dayId)
            }
        }
        bind()
    }

    private fun navigateToWaitingFragment(dayId: Int) {
        if (isDialogShow) {
            MaterialAlertDialogBuilder(requireContext())
                .setCancelable(false)
                .setNegativeButton(R.string.resume_day) { _, _ ->
                    findNavController().navigate(
                        R.id.action_exerciseListFragment_to_waitingFragment,
                        bundleOf(DAY_ID to dayId)
                    )
                }
                .setPositiveButton(R.string.repeat_day) { _, _ ->
                    viewModel.update(ExercisesIntent.RepeatDay)
                    findNavController().navigate(
                        R.id.action_exerciseListFragment_to_waitingFragment,
                        bundleOf(DAY_ID to dayId)
                    )
                }.show()
        } else {
            viewModel.update(ExercisesIntent.RepeatDay)
            findNavController().navigate(
                R.id.action_exerciseListFragment_to_waitingFragment,
                bundleOf(DAY_ID to dayId)
            )
        }
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
                var completeExerciseCount = 0
                listExercises.clear()
                listExercises.addAll(result.data)
                listExercises.forEach { if (it.isComplete) completeExerciseCount++ }
                isDialogShow =
                    completeExerciseCount > 0 && completeExerciseCount != result.data.size
                showContent(result.data, completeExerciseCount == result.data.size)
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

    private fun showContent(data: List<ExerciseItem>, dayIsComplete: Boolean) = with(binding) {
        adapter.submitList(data)
        tvError.isVisible = false
        botStart.isVisible = true
        tvExercisesList.isVisible = true
        progressRequest.isVisible = false
        botStart.text = if (dayIsComplete) {
            getString(R.string.repeat_day)
        } else {
            getString(R.string.start)
        }
    }
}