package com.example.myfitnessnote.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.FragmentWaitingBinding
import com.example.myfitnessnote.utils.BindingFragment
import com.example.myfitnessnote.utils.DAY_ID
import com.example.myfitnessnote.utils.showExerciseProgress
import kotlinx.coroutines.launch


class WaitingFragment : BindingFragment<FragmentWaitingBinding>() {
    private var dayId = 0

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWaitingBinding {
        return FragmentWaitingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayId = requireArguments().getInt(DAY_ID)
        bind()
    }

    private fun bind() = with(binding) {
        lifecycleScope.launch {
            showExerciseProgress(
                COUNTER_START_VALUE,
                lifecycleScope,
                tvWaitCounter,
                tvWaitProgress
            ) {
                findNavController().navigate(
                    R.id.action_waitingFragment_to_currentExerciseFragment,
                    bundleOf(DAY_ID to dayId)
                )
            }
        }
    }

    companion object {
        private const val COUNTER_START_VALUE = 3000
    }
}