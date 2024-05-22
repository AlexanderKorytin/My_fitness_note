package com.example.myfitnessnote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myfitnessnote.databinding.FragmentWaitingBinding
import com.example.myfitnessnote.utils.BindingFragment
import com.example.myfitnessnote.utils.showExerciseProgress
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
       showExerciseProgress(COUNTER_START_VALUE, lifecycleScope, tvWaitCounter, tvWaitProgress){
           findNavController().navigateUp()
       }
    }

    companion object {
        private const val COUNTER_START_VALUE = 5000
    }
}