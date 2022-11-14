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
import com.example.mypet.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
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
        binding.buttonSignUp.isEnabled = false
        binding.emailField.addTextChangedListener(textWatcher)
        binding.passwordField.addTextChangedListener(textWatcher2)
        binding.nameField.addTextChangedListener(textWatcher3)
        binding.surnameField.addTextChangedListener(textWatcher4)
        binding.phoneField.addTextChangedListener(textWatcher5)
        binding = FragmentSignUpBinding.inflate(layoutInflater)

        return binding.root
    }
}