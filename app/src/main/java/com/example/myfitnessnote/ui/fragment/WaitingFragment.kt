package com.example.myfitnessnote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myfitnessnote.databinding.FragmentWaitingBinding
import com.example.myfitnessnote.utils.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


class WaitingFragment : BindingFragment<FragmentWaitingBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWaitingBinding {
        return FragmentWaitingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() = with(binding) {
        tvWaitProgress.max = COUNTER_START_VALUE
        var progressCurrentValue = COUNTER_START_VALUE
        val processStep = PROGRESS_DELAY.toInt()
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val counterJob = lifecycleScope.launch {
                for (i in COUNTER_START_VALUE downTo COUNTER_END_VALUE step COUNTER_STEP) {
                    tvWaitCounter.text = SimpleDateFormat("mm:ss", Locale.getDefault()).format(i)
                    delay(COUNTER_DELAY)
                }
            }
            val progressJob = lifecycleScope.launch(Dispatchers.IO) {
                while (progressCurrentValue >= 0) {
                    delay(PROGRESS_DELAY)
                    progressCurrentValue -= processStep
                    tvWaitProgress.progress = progressCurrentValue
                }
            }
            progressJob.join()
            TODO("navigateTo next fragment")
        }
    }

    companion object {
        private const val COUNTER_DELAY = 1000L
        private const val PROGRESS_DELAY = 100L
        private const val COUNTER_START_VALUE = 5000
        private const val COUNTER_END_VALUE = 0
        private const val COUNTER_STEP = 1000
    }
}