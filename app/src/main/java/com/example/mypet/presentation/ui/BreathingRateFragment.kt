package com.example.mypet.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypet.databinding.FragmentBreathingRateBinding
import com.example.mypet.domain.Repository

class BreathingRateFragment : Fragment() {

    private lateinit var binding: FragmentBreathingRateBinding
    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBreathingRateBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object{
        fun newInstance(repository: Repository) = BreathingRateFragment().apply {
            this.repository = repository
        }
    }

}