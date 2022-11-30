package com.example.mypet.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.FragmentBreathingRateBinding
import com.example.mypet.databinding.FragmentCustomIndicatorsBinding
import com.example.mypet.domain.Period
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory
import java.lang.StringBuilder


class CustomIndicatorsFragment : Fragment() {

    private lateinit var binding: FragmentCustomIndicatorsBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCustomIndicatorsBinding.inflate(layoutInflater)
        val callback = requireActivity() as MainActivityCallback
        viewModelInit()

        binding.buttonMoreData.setOnClickListener {
            callback.showMoreData()
        }

        val pets = callback.getPets()
        if (pets.isNotEmpty()) {
            viewModel.getParameters(pets[0].id, Period.HOUR)
            viewModel.getParametersLiveData().observe(requireActivity(), Observer {
                it?.let {
                    binding.breathingRate.text = it[0].breathingRate.toString()
                    binding.temp.text = it[0].temperature.toString()
                    binding.heartRate.text = it[0].heartRate.toString()
                    binding.pressure.text = StringBuilder()
                        .append(it[0].pressure.topPressure.toString())
                        .append("/")
                        .append(it[0].pressure.lowerPressure.toString())
                }
            })
        }
        return binding.root
    }

    private fun viewModelInit(){
        viewModelFactory = ViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PetViewModel::class.java)
    }
}