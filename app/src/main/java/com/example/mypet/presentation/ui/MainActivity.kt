package com.example.mypet.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mypet.R
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.ActivityMainBinding

interface MainActivityCallback{
    fun showFragment(fragment: Fragment): Boolean
    fun showProfileEditingFragment()
    fun exit()
    fun showMoreData()
}

class MainActivity : AppCompatActivity(), MainActivityCallback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getIntExtra("clientId", -1)

        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){
                R.id.profile -> showFragment(ProfileFragment())
                R.id.indicators -> showFragment(CustomIndicatorsFragment())
                R.id.settings -> showFragment(SettingsFragment())
                else -> {false}
            }
        }
    }

    override fun showFragment(fragment: Fragment): Boolean{
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView2.id, fragment)
            .commit()
        return true
    }

    override fun showProfileEditingFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView2.id, ProfileEditingFragment())
            .commit()
    }

    override fun exit() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showMoreData() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView2.id, MoreDataFragment())
            .commit()
    }
}
