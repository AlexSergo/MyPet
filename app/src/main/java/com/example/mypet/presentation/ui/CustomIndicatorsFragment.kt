package com.example.mypet.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypet.databinding.FragmentBreathingRateBinding
import com.example.mypet.databinding.FragmentCustomIndicatorsBinding


class CustomIndicatorsFragment : Fragment() {

    private lateinit var binding: FragmentCustomIndicatorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCustomIndicatorsBinding.inflate(layoutInflater)

        binding.buttonMoreData.setOnClickListener {
            val callback = requireActivity() as MainActivityCallback
            callback.showMoreData()
        }
        return binding.root
    }
}