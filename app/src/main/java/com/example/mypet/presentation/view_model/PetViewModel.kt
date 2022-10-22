package com.example.mypet.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.BreathingRate
import com.example.mypet.domain.model.HeartRate
import com.example.mypet.domain.model.Pressure
import com.example.mypet.domain.model.Temperature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(private val repository: Repository): ViewModel() {

    private val temperatureLiveData = MutableLiveData<List<Temperature>>()
    private val pressureLiveData = MutableLiveData<List<Pressure>>()
    private val heartRateLiveData = MutableLiveData<List<HeartRate>>()
    private val breathingRateLiveData = MutableLiveData<List<BreathingRate>>()

    fun getTemperatureLiveData(): LiveData<List<Temperature>>{
        return temperatureLiveData
    }

    fun getPressureLiveData(): LiveData<List<Pressure>>{
        return pressureLiveData
    }

    fun getHeartRateLiveData(): LiveData<List<HeartRate>>{
        return heartRateLiveData
    }

    fun getBreathingRateLiveData(): LiveData<List<BreathingRate>>{
        return breathingRateLiveData
    }

    fun getTemperatureForDay() = viewModelScope.launch(Dispatchers.IO){

    }
    fun getTemperatureForWeek() = viewModelScope.launch(Dispatchers.IO) {

    }

    fun getTemperatureForMonth() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getPressureForDay() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getPressureForWeek() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getPressureForMonth() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getHeartRateForDay() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getHeartRateForWeek() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getHeartRateForMonth() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getBreathingRateForDay() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getBreathingRateForWeek() = viewModelScope.launch(Dispatchers.IO){

    }

    fun getBreathingRateForMonth() = viewModelScope.launch(Dispatchers.IO){

    }
}