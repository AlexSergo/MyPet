package com.example.mypet.presentation.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mypet.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var sPref : SharedPreferences
    private var low_temp : Int = 0
    private var high_temp : Int = 0
    private val SAVED_LOW_TEMP : String = "low_temp"
    private val SAVED_HIGH_TEMP : String = "high_temp"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        loadText()
        low_temp = binding.editLowTemp.text.toString().toInt()
        high_temp = binding.editHighTemp.text.toString().toInt()
        binding.btnLess1.setOnClickListener{
            low_temp--
            binding.editLowTemp.apply { text = low_temp.toString() }
        }
        binding.btnMore1.setOnClickListener{
            low_temp++
            binding.editLowTemp.apply { text = low_temp.toString() }
        }
        binding.btnLess2.setOnClickListener{
            high_temp--
            binding.editHighTemp.apply { text = high_temp.toString() }
        }
        binding.btnMore2.setOnClickListener{
            high_temp++
            binding.editHighTemp.apply { text = high_temp.toString() }
        }
        return binding.root
    }

    private fun saveText() {
        sPref = requireActivity().getSharedPreferences("MyPref", MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString(SAVED_LOW_TEMP, binding.editLowTemp.text.toString())
        ed.apply()
        ed.putString(SAVED_HIGH_TEMP, binding.editHighTemp.text.toString())
        ed.apply()
        Toast.makeText(requireActivity(), "Text saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadText() {
        sPref = requireActivity().getSharedPreferences("MyPref", MODE_PRIVATE)
        val savedText = sPref.getString(SAVED_LOW_TEMP, "")
        val savedHighTemp = sPref.getString(SAVED_HIGH_TEMP, "")
        binding.editLowTemp.apply { text = savedText}
        binding.editHighTemp.apply { text = savedHighTemp}
        Toast.makeText(requireActivity(), "Text loaded", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveText()
    }
}