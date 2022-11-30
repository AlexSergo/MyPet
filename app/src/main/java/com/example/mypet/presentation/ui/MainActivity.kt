package com.example.mypet.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mypet.R
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.ActivityMainBinding
import com.example.mypet.domain.model.pet.Pet
import com.example.mypet.domain.model.user.User
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory

interface MainActivityCallback{
    fun showFragment(fragment: Fragment): Boolean
    fun showProfileEditingFragment()
    fun exit()
    fun showMoreData()
    fun getUser(): User
    fun getPets(): List<Pet>
}

class MainActivity : AppCompatActivity(), MainActivityCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User
    private var pets = listOf<Pet>()
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelInit()

        user = User(
            id = intent.getIntExtra("clientId", -1),
            name = intent.getStringExtra("clientName")!!,
            email = intent.getStringExtra("clientEmail")!!)

            viewModel.getPets(user.id)
        viewModel.getPetsLiveData().observe(this, Observer {
            it?.let {
                pets = it
            }
        })

        binding.bottomNavigation.setOnItemSelectedListener {
            binding.bottomNavigation.itemIconTintList = null
            when(it.itemId){
                R.id.profile -> showFragment(ProfileFragment())
                R.id.indicators -> showFragment(CustomIndicatorsFragment())
                R.id.settings -> showFragment(SettingsFragment())
                else -> {false}
            }
        }
    }

    private fun viewModelInit(){
        viewModelFactory = ViewModelFactory(RepositoryInitializer.getRepository(this))
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PetViewModel::class.java)
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

    override fun getUser(): User {
        return user
    }

    override fun getPets(): List<Pet> {
        return pets
    }
}
