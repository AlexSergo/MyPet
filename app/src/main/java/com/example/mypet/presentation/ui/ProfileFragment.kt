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
import com.example.mypet.databinding.FragmentProfileBinding
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        viewModelInit()
        setClickListeners()

        val callback = requireActivity() as MainActivityCallback
        val user = callback.getUser()
        binding.emailOfUser.text = user.email
        binding.nameOfUser.text = user.name

        return binding.root
    }

    private fun viewModelInit(){
        viewModelFactory = ViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PetViewModel::class.java)
    }

    private fun setClickListeners(){
        val callback = requireActivity() as MainActivityCallback
        binding.edit.setOnClickListener {
            callback.showProfileEditingFragment()
        }
        binding.exit.setOnClickListener {
            callback.exit()
        }
    }
}