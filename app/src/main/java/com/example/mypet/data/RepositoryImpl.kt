package com.example.mypet.data

import com.example.mypet.data.local_data_source.PetDao
import com.example.mypet.data.remote_data_source.PetApi
import com.example.mypet.data.remote_data_source.model.BreathingRateResponse
import com.example.mypet.data.remote_data_source.model.HeartRateResponse
import com.example.mypet.data.remote_data_source.model.PressureResponse
import com.example.mypet.data.remote_data_source.model.TemperatureResponse
import com.example.mypet.domain.Repository

class RepositoryImpl(private val api: PetApi, private val petDao: PetDao): Repository {

    override suspend fun getTemperatureForDay(): TemperatureResponse {
        return api.getTemperatureForDay()
    }

    override suspend fun getTemperatureForWeek(): TemperatureResponse {
        return api.getTemperatureForWeek()
    }

    override suspend fun getTemperatureForMonth(): TemperatureResponse {
        return api.getTemperatureForMonth()
    }

    override suspend fun getPressureForDay(): PressureResponse {
        return api.getPressureForDay()
    }

    override suspend fun getPressureForWeek(): PressureResponse {
        return api.getPressureForWeek()
    }

    override suspend fun getPressureForMonth(): PressureResponse {
        return api.getPressureForMonth()
    }

    override suspend fun getHeartRateForDay(): HeartRateResponse {
        return api.getHeartRateForDay()
    }

    override suspend fun getHeartRateForWeek(): HeartRateResponse {
        return api.getHeartRateForWeek()
    }

    override suspend fun getHeartRateForMonth(): HeartRateResponse {
        return api.getHeartRateForMonth()
    }

    override suspend fun getBreathingRateForDay(): BreathingRateResponse {
        return api.getBreathingRateForDay()
    }

    override suspend fun getBreathingRateForWeek(): BreathingRateResponse {
        return api.getBreathingRateForWeek()
    }

    override suspend fun getBreathingRateForMonth(): BreathingRateResponse {
        return api.getBreathingRateForWeek()
    }
}