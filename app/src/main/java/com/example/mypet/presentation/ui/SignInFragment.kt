package com.example.mypet.presentation.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.FragmentSignInBinding
import com.example.mypet.domain.model.user.UserLogin
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PetViewModel
    private val EMAIL = "email"
    private val PASSWORD = "password"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val callback = requireActivity() as RegisterActivityCallback
        binding = FragmentSignInBinding.inflate(layoutInflater)

        viewModelInit()

        binding.buttonSignIn.isEnabled = true
        binding.register.setOnClickListener {
            callback.showSignUpFragment()
        }

        binding.buttonSignIn.setOnClickListener {
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            viewModel.loginClient(UserLogin
                (email = email,
                password = password))

            viewModel.getUserLiveData().observe(requireActivity(), Observer{
                it?.let {
                    saveUserData(email, password)
                    callback.showProfile(it)
                }
            })
        }
        return binding.root
    }

    private fun saveUserData(email: String, password: String) {
        val sPref = requireActivity().getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString(EMAIL,email)
        ed.apply()
        ed.putString(PASSWORD, password)
        ed.apply()
    }

    private fun viewModelInit(){
        viewModelFactory = ViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PetViewModel::class.java)
    }

}

