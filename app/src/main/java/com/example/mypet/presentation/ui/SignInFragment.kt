package com.example.mypet.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypet.R
import com.example.mypet.databinding.FragmentBreathingRateBinding
import com.example.mypet.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val callback = requireActivity() as RegisterActivityCallback
        binding = FragmentSignInBinding.inflate(layoutInflater)

        binding.register.setOnClickListener {
            callback.showSignUpFragment()
        }

        binding.buttonSignIn.setOnClickListener {
            callback.showProfile()
        }

        return binding.root
    }
}