package com.example.mypet.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mypet.R
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.FragmentMoreDataBinding
import com.example.mypet.domain.Period
import com.example.mypet.presentation.StringDate
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
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

        var period = Period.DAY
        val today = StringDate.getToday()
        binding.currentDate.text =  today

        binding.dayButton.setOnClickListener {
            period = Period.DAY
            binding.dayButton.background = R.color.active_button.toDrawable()
            binding.weekButton.background = R.color.white.toDrawable()
            binding.monthButton.background = R.color.white.toDrawable()
            binding.currentDate.text =  today
        }
        binding.weekButton.setOnClickListener {
            period = Period.WEEK
            binding.weekButton.background = R.color.active_button.toDrawable()
            binding.dayButton.background = R.color.white.toDrawable()
            binding.monthButton.background = R.color.white.toDrawable()
            binding.currentDate.text = StringDate.getPreviousDate(today, 7) + " - " + today
        }
        binding.monthButton.setOnClickListener {
            period = Period.MONTH
            binding.monthButton.background = R.color.active_button.toDrawable()
            binding.dayButton.background = R.color.white.toDrawable()
            binding.weekButton.background = R.color.white.toDrawable()
            val daysInMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
            binding.currentDate.text = StringDate.getPreviousDate(today, daysInMonth)+ " - " + today
        }
        val graphs = getParametersForPeriod(period)
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

    private fun getParametersForPeriod(period: Period): MutableMap<String, Graph>{
        val graphMap = mutableMapOf<String, Graph>()
        val callback = requireActivity() as MainActivityCallback
        val pets = callback.getPets()
        if (pets.isNotEmpty()) {
            viewModel.getParameters(pets[0].id, period)
            viewModel.getParametersLiveData().observe(requireActivity(), Observer{
                it?.let {
                    val tempValue = mutableListOf<Float>()
                    val breathingRateValue = mutableListOf<Float>()
                    val heartRateValue = mutableListOf<Float>()
                    val topPressureValue = mutableListOf<Float>()
                    val lowPressureValue = mutableListOf<Float>()
                    val time = mutableListOf<String>()
                    var i = 0
                    for (params in it) {
                        tempValue.add(params.temperature.temperature)
                        heartRateValue.add(params.heartRate.heartRate.toFloat())
                        topPressureValue.add(params.pressure.topPressure.toFloat())
                        lowPressureValue.add(params.pressure.lowerPressure.toFloat())
                        breathingRateValue.add(params.breathingRate.breathingRate.toFloat())
                        time.add((params.date.substring(11, 13).toInt() + i).toString())
                        i++
                    }
                    graphMap["Температура"] = Graph("Температура", time, tempValue)
                    graphMap["Частота дыхания"] =
                        Graph("Частота дыхания", time, breathingRateValue)
                    graphMap["ЧСС"] = Graph("ЧСС", time, heartRateValue)
                    graphMap["Давление"] = Graph("Давление", time, topPressureValue)
                }
            })
        }
        return graphMap
    }
}