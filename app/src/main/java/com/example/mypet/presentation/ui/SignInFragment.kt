package com.example.mypet.presentation.ui

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
import com.example.mypet.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    var count = 0
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) { count++ }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    private val textWatcher2 = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (count == 1) {
                binding.buttonSignIn.isEnabled = true
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val callback = requireActivity() as RegisterActivityCallback
        binding = FragmentSignInBinding.inflate(layoutInflater)
        binding.buttonSignIn.isEnabled = false
        binding.emailField.addTextChangedListener(textWatcher)
        binding.passwordField.addTextChangedListener(textWatcher2)
        binding.register.setOnClickListener {
            callback.showSignUpFragment()
        }

        binding.buttonSignIn.setOnClickListener {
            callback.showProfile()
        }
        return binding.root
    }

}

