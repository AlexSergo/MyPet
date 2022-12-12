package com.example.mypet.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mypet.data.RepositoryInitializer
import com.example.mypet.databinding.ActivityStartBinding
import com.example.mypet.domain.model.user.User
import com.example.mypet.domain.model.user.UserLogin
import com.example.mypet.presentation.view_model.PetViewModel
import com.example.mypet.presentation.view_model.ViewModelFactory

interface RegisterActivityCallback{
    fun showSignUpFragment()
    fun showProfile(user: User)
}

data class SavedUser(
    val email: String,
    val password: String
)

class StartActivity : AppCompatActivity(), RegisterActivityCallback {

    private lateinit var binding: ActivityStartBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PetViewModel
    private val EMAIL = "email"
    private val PASSWORD = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelInit()

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = loadUserData()
            if (user != null) {
                viewModel.loginClient(UserLogin
                    (email = user.email,
                    password = user.password))

                viewModel.getUserLiveData().observe(this, Observer{
                    it?.let {
                        showProfile(it)
                    }
                })
            }
            else
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, SignInFragment())
            .commit()

    }

    private fun loadUserData(): SavedUser? {
        val sPref = this.getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        val email= sPref.getString(EMAIL, null)
        val password = sPref.getString(PASSWORD, null)
        if (email != null && password != null)
            return SavedUser(email = email, password = password)
        return null
    }

    private fun viewModelInit(){
        viewModelFactory = ViewModelFactory(RepositoryInitializer.getRepository(context = this))
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PetViewModel::class.java)
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