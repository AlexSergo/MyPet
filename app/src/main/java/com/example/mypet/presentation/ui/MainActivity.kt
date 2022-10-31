package com.example.mypet.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mypet.R
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        BreathingRateFragment.newInstance(RepositoryInitializer.getRepository(context = this))
        setContentView(binding.root)
    }
}