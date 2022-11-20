package com.example.mypet.presentation.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mypet.R
import com.example.mypet.databinding.ActivityStartBinding
import com.example.mypet.domain.model.user.User

interface RegisterActivityCallback{
    fun showSignUpFragment()
    fun showProfile(user: User)
}

class StartActivity : AppCompatActivity(), RegisterActivityCallback {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showSignUpFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, SignUpFragment())
            .commit()
    }

    override fun showProfile(user: User) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("clientId", user.id)
        intent.putExtra("clientName", user.name)
        intent.putExtra("clientEmail", user.email)
        startActivity(intent)
        finish()
    }
}