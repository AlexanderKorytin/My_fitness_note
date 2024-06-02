package com.example.myfitnessnote.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.FragmentCurrentExerciseBinding
import com.example.myfitnessnote.domain.models.DurationLabel
import com.example.myfitnessnote.domain.models.ExerciseItem
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesIntent
import com.example.myfitnessnote.presentetion.models.exercises.ExercisesScreenState
import com.example.myfitnessnote.presentetion.viewmodel.ExerciseViewModel
import com.example.myfitnessnote.utils.BindingFragment
import com.example.myfitnessnote.utils.DAY_ID
import com.example.myfitnessnote.utils.showExerciseProgress
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CurrentExerciseFragment : BindingFragment<FragmentCurrentExerciseBinding>() {
    private var dayId = 0
    private val viewModel: ExerciseViewModel by viewModel<ExerciseViewModel> {
        parametersOf(
            arguments?.getInt(DAY_ID) ?: 0
        )
    }
    private var job: Job? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCurrentExerciseBinding {
        return FragmentCurrentExerciseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayId = requireArguments().getInt(DAY_ID)
        bind()
    }

    private fun bind() {
        viewModel.update(ExercisesIntent.RequestExercises)
        viewModel.screenState.observe(viewLifecycleOwner) {
            progressResult(it)
        }
        binding.butNextExercise.setOnClickListener {
            if (job != null && job!!.isActive) {
                job?.cancel()
            }
            viewModel.update(ExercisesIntent.UpdateCounter)
        }
    }

    private fun progressResult(result: ExercisesScreenState) {
        when (result) {
            is ExercisesScreenState.Content -> {
                showContent(result.data, result.counter)
            }

            is ExercisesScreenState.Loading -> {
                //   showLoading()
            }

            is ExercisesScreenState.Error -> {
                //    showError()
            }
        }
    }

    private fun showContent(list: List<ExerciseItem>, counter: Int) = with(binding) {
        if (counter < list.size) {
            Glide.with(requireContext())
                .load("${FILE_PATH}${FILE_SEPARATOR}${list[counter].icon}")
                .placeholder(R.drawable.ic_cancel)
                .centerCrop()
                .into(tvCurrentIcon)
            tvCurrentName.text = list[counter].name
            tvNextName.text = list.getOrNull(counter + 1)?.name ?: DEFAULT_NAME
            Glide.with(requireContext())
                .load("${FILE_PATH}${FILE_SEPARATOR}${list.getOrNull(counter + 1)?.icon}")
                .placeholder(R.drawable.ic_cancel)
                .centerInside()
                .into(tvNextIcon)
            showDuration(list, counter)
        } else {
            findNavController().navigate(
                R.id.action_currentExerciseFragment_to_dayFinalFragment,
                bundleOf(DAY_ID to dayId)
            )
        }
    }

    private fun showDuration(list: List<ExerciseItem>, counter: Int) = with(binding) {
        if (list[counter].durationLabel == DurationLabel.TIME) {
            job = lifecycleScope.launch {
                showExerciseProgress(
                    getDuration(list[counter].duration),
                    this,
                    tvCounter = tvCurrentDuration,
                    tvProcess = tvCurrentProgress
                ) {
                    viewModel.update(ExercisesIntent.UpdateCounter)
                }
            }
        } else {
            job?.cancel()
            tvCurrentDuration.text = "${list[counter].duration} повторений"
        }
    }

    private fun getDuration(time: Int): Int {
        return time * TIME_CONVERTOR
    }
}

private const val FILE_PATH = "file://"
private const val FILE_SEPARATOR = "/android_asset/"
private const val DEFAULT_NAME = "Больше упражнений нет"
private const val TIME_CONVERTOR = 1000