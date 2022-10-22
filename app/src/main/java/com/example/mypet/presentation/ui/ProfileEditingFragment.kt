package com.example.mypet.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypet.databinding.FragmentBreathingRateBinding
import com.example.mypet.databinding.FragmentPressureBinding
import com.example.mypet.databinding.FragmentProfileEditingBinding

class ProfileEditingFragment : Fragment() {

    private lateinit var binding: FragmentProfileEditingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileEditingBinding.inflate(layoutInflater)
        return binding.root
    }
}