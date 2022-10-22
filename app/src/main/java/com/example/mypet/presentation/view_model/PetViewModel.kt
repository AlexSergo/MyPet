package com.example.mypet.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.domain.Period
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.BreathingRate
import com.example.mypet.domain.model.HeartRate
import com.example.mypet.domain.model.Pressure
import com.example.mypet.domain.model.Temperature
import com.example.mypet.domain.use_cases.GetBreathingRateUseCase
import com.example.mypet.domain.use_cases.GetHeartRateUseCase
import com.example.mypet.domain.use_cases.GetPressureUseCase
import com.example.mypet.domain.use_cases.GetTemperatureUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(private val repository: Repository): ViewModel() {

    private val temperatureLiveData = MutableLiveData<List<Temperature>>()
    private val pressureLiveData = MutableLiveData<List<Pressure>>()
    private val heartRateLiveData = MutableLiveData<List<HeartRate>>()
    private val breathingRateLiveData = MutableLiveData<List<BreathingRate>>()

    private val getTemperatureUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetTemperatureUseCase(repository)
    }

    private val getPressureUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetPressureUseCase(repository)
    }

    private val getHeartRateUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetHeartRateUseCase(repository)
    }

    private val getBreathingRateUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetBreathingRateUseCase(repository)
    }

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
        temperatureLiveData.postValue(getTemperatureUseCase.execute(Period.DAY))

    }
    fun getTemperatureForWeek() = viewModelScope.launch(Dispatchers.IO) {
        temperatureLiveData.postValue(getTemperatureUseCase.execute(Period.WEEK))
    }

    fun getTemperatureForMonth() = viewModelScope.launch(Dispatchers.IO){
        temperatureLiveData.postValue(getTemperatureUseCase.execute(Period.MONTH))
    }

    fun getPressureForDay() = viewModelScope.launch(Dispatchers.IO){
        pressureLiveData.postValue(getPressureUseCase.execute(Period.DAY))
    }

    fun getPressureForWeek() = viewModelScope.launch(Dispatchers.IO){
        pressureLiveData.postValue(getPressureUseCase.execute(Period.WEEK))
    }

    fun getPressureForMonth() = viewModelScope.launch(Dispatchers.IO){
        pressureLiveData.postValue(getPressureUseCase.execute(Period.MONTH))
    }

    fun getHeartRateForDay() = viewModelScope.launch(Dispatchers.IO){
        heartRateLiveData.postValue(getHeartRateUseCase.execute(Period.DAY))
    }

    fun getHeartRateForWeek() = viewModelScope.launch(Dispatchers.IO){
        heartRateLiveData.postValue(getHeartRateUseCase.execute(Period.WEEK))
    }

    fun getHeartRateForMonth() = viewModelScope.launch(Dispatchers.IO){
        heartRateLiveData.postValue(getHeartRateUseCase.execute(Period.MONTH))
    }

    fun getBreathingRateForDay() = viewModelScope.launch(Dispatchers.IO){
        breathingRateLiveData.postValue(getBreathingRateUseCase.execute(Period.DAY))
    }

    fun getBreathingRateForWeek() = viewModelScope.launch(Dispatchers.IO){
        breathingRateLiveData.postValue(getBreathingRateUseCase.execute(Period.WEEK))
    }

    fun getBreathingRateForMonth() = viewModelScope.launch(Dispatchers.IO){
        breathingRateLiveData.postValue(getBreathingRateUseCase.execute(Period.MONTH))
    }
}