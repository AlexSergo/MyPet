package com.example.mypet.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.FragmentMoreDataBinding
import com.example.mypet.domain.Period
import com.example.mypet.domain.model.parameters.*
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset.UTC
import java.time.format.DateTimeFormatter
import java.util.*

class MoreDataFragment : Fragment() {

    private lateinit var binding: FragmentMoreDataBinding
    private lateinit var adapter: GraphAdapter
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModelInit()
        binding = FragmentMoreDataBinding.inflate(layoutInflater)
        adapter = GraphAdapter()
        binding.recyclerviewItems.adapter = adapter

        val graphs = getParametersForPeriod()
        chipGroupCheck(graphs)
        return binding.root
    }

    private fun chipGroupCheck(graphMap: Map<String, Graph>){
        binding.chipGroup.setOnCheckedStateChangeListener{ group, checkedId ->
            val graphList = mutableListOf<Graph>()
            for (id in checkedId) {
                val chip = group.findViewById<Chip>(id)
                when (chip){
                    binding.tempChip ->
                        graphMap.get("Температура")?.let { graphList.add(it) }
                    binding.breathChip ->
                        graphMap.get("Частота дыхания")?.let { graphList.add(it) }
                    binding.heartChip ->
                        graphMap.get("ЧСС")?.let { graphList.add(it) }
                    binding.presChip ->
                        graphMap.get("Давление")?.let { graphList.add(it) }
                }
            }
            adapter.set(graphList)
        }
    }

    private fun viewModelInit(){
        viewModelFactory = ViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PetViewModel::class.java)
    }

    private fun getParametersForPeriod(): MutableMap<String, Graph>{
        val graphMap = mutableMapOf<String, Graph>()
        val callback = requireActivity() as MainActivityCallback
    //    val pets = callback.getPets()
       // if (pets.isNotEmpty()) {

            val parameters = mutableListOf<Parameters>()
            parameters.add(Parameters(
                breathingRate = BreathingRate(50),
                heartRate = HeartRate(30),
                temperature = Temperature(38.7f),
                date = "0",
                pressure = Pressure(topPressure = 110, lowerPressure = 90)
            ))
            parameters.add(Parameters(
                breathingRate = BreathingRate(60),
                heartRate = HeartRate(25),
                temperature = Temperature(37.9f),
                date = "1",
                pressure = Pressure(topPressure = 105, lowerPressure = 93)
            ))
            parameters.add(Parameters(
                breathingRate = BreathingRate(55),
                heartRate = HeartRate(35),
                temperature = Temperature(38.0f),
                date = "2",
                pressure = Pressure(topPressure = 109, lowerPressure = 85)
            ))
            parameters.add(Parameters(
                breathingRate = BreathingRate(53),
                heartRate = HeartRate(39),
                temperature = Temperature(37.5f),
                date = "3",
                pressure = Pressure(topPressure = 112, lowerPressure = 91)
            ))
        parameters.add(Parameters(
            breathingRate = BreathingRate(53),
            heartRate = HeartRate(39),
            temperature = Temperature(37.0f),
            date = "4",
            pressure = Pressure(topPressure = 112, lowerPressure = 91)
        ))
         //   viewModel.getParameters(pets[0].id, Period.HOUR)
        //    viewModel.getParametersLiveData().observe(requireActivity(), Observer{
               // it?.let {
                    val tempValue = mutableListOf<Float>()
                    val breathingRateValue = mutableListOf<Float>()
                    val heartRateValue = mutableListOf<Float>()
                    val topPressureValue = mutableListOf<Float>()
                    val lowPressureValue = mutableListOf<Float>()
                    val period = mutableListOf<String>()
                    for (params in parameters) {
                        tempValue.add(params.temperature.temperature)
                        heartRateValue.add(params.heartRate.heartRate.toFloat())
                        topPressureValue.add(params.pressure.topPressure.toFloat())
                        lowPressureValue.add(params.pressure.lowerPressure.toFloat())
                        breathingRateValue.add(params.breathingRate.breathingRate.toFloat())
                        period.add(params.date)
                    }
                    graphMap["Температура"] = Graph("Температура", period, tempValue)
                    graphMap["Частота дыхания"] =
                        Graph("Частота дыхания", period, breathingRateValue)
                    graphMap["ЧСС"] = Graph("ЧСС", period, heartRateValue)
                    graphMap["Давление"] = Graph("Давление", period, topPressureValue)
                //}
            //})
      //  }
        return graphMap
    }
}