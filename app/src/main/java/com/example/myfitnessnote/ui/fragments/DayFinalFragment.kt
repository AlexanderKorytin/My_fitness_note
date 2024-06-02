package com.example.myfitnessnote.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myfitnessnote.R
import com.example.myfitnessnote.databinding.FragmentDayFinalBinding
import com.example.myfitnessnote.presentetion.viewmodel.DayFinalViewModel
import com.example.myfitnessnote.utils.BindingFragment
import com.example.myfitnessnote.utils.DAY_ID
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DayFinalFragment : BindingFragment<FragmentDayFinalBinding>() {
    private var dayId = 0
    private val viewModel by viewModel<DayFinalViewModel> {
        parametersOf(
            arguments?.getInt(DAY_ID) ?: 0
        )
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDayFinalBinding {
        return FragmentDayFinalBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        dayId = requireArguments().getInt(DAY_ID)
        binding.butReturnToDaysList.setOnClickListener {
            findNavController().navigate(R.id.action_dayFinalFragment_to_daysFragment)
        }
    }

    private fun bind() = with(binding) {
        viewModel.saveDaysList()
        Glide.with(requireContext())
            .load("${FILE_PATH}${FILE_SEPARATOR}${FILE_NAME}")
            .placeholder(R.drawable.ic_cancel)
            .centerInside()
            .into(tvFirework)
    }
}

private const val FILE_PATH = "file://"
private const val FILE_SEPARATOR = "/android_asset/"
private const val FILE_NAME = "day_final.gif"
