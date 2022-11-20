package com.example.mypet.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.domain.Period
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.parameters.Parameters
import com.example.mypet.domain.model.pet.Pet
import com.example.mypet.domain.model.user.User
import com.example.mypet.domain.model.user.UserLogin
import com.example.mypet.domain.model.user.UserRegister
import com.example.mypet.domain.use_cases.GetParametersUseCase
import com.example.mypet.domain.use_cases.GetPetsUseCase
import com.example.mypet.domain.use_cases.LoginUserUseCase
import com.example.mypet.domain.use_cases.RegisterUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(private val repository: Repository): ViewModel() {

    private val petsLivedata = MutableLiveData<List<Pet>>()
    private val userLiveData = MutableLiveData<User>()
    private val parametersLiveData = MutableLiveData<List<Parameters>>()

    private val getPetsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetPetsUseCase(repository)
    }

    private val registerUserUseCase by lazy(LazyThreadSafetyMode.NONE) {
        RegisterUserUseCase(repository)
    }

    private val getParametersUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetParametersUseCase(repository)
    }

    private val loginUserUseCase by lazy(LazyThreadSafetyMode.NONE){
        LoginUserUseCase(repository)
    }

    fun getPetsLiveData(): LiveData<List<Pet>>{
        return petsLivedata
    }

    fun getUserLiveData(): LiveData<User>{
        return userLiveData
    }

    fun getParametersLiveData(): LiveData<List<Parameters>>{
        return parametersLiveData
    }

    fun getPets(ownerId: Int) = viewModelScope.launch(Dispatchers.IO) {
        petsLivedata.postValue(getPetsUseCase.execute(ownerId))
    }

    fun createUser(user: UserRegister) = viewModelScope.launch(Dispatchers.IO) {
        userLiveData.postValue(registerUserUseCase.execute(user))
    }

    fun getParameters(petId: Int, period: Period) = viewModelScope.launch(Dispatchers.IO) {
        parametersLiveData.postValue(getParametersUseCase.execute(petId, period))
    }

    fun loginClient(user: UserLogin) = viewModelScope.launch(Dispatchers.IO){
        userLiveData.postValue(loginUserUseCase.execute(user))
    }
}