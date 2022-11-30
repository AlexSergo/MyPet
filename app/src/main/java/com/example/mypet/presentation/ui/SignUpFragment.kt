package com.example.mypet.presentation.ui

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
import com.example.mypet.databinding.FragmentSignUpBinding
import com.example.mypet.domain.model.user.UserRegister
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PetViewModel

    var count = 0
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) { count++ }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    private val textWatcher2 = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) { count++ }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    private val textWatcher3 = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) { count++ }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    private val textWatcher4 = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) { count++ }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    private val textWatcher5 = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (count == 4)
                binding.buttonSignUp.isEnabled = true
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        addTextChangedListeners()
        viewModelInit()

        binding.buttonSignUp.setOnClickListener {
            viewModel.createUser(
                UserRegister(username = binding.emailField.text.toString(),
                   email = binding.emailField.text.toString(),
                    phone = binding.phoneField.text.toString(),
                    password = binding.passwordField.text.toString(),
                    name = binding.nameField.text.toString()))
            viewModel.getUserLiveData().observe(requireActivity(), Observer{
                it?.let {
                   val callback = requireActivity() as RegisterActivityCallback
                    callback.showProfile(it)
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

    private fun addTextChangedListeners(){
        binding.buttonSignUp.isEnabled = true
        binding.emailField.addTextChangedListener(textWatcher)
        binding.passwordField.addTextChangedListener(textWatcher2)
        binding.nameField.addTextChangedListener(textWatcher3)
        binding.surnameField.addTextChangedListener(textWatcher4)
        binding.phoneField.addTextChangedListener(textWatcher5)
    }
}