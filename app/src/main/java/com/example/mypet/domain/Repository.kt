package com.example.mypet.domain

import com.example.mypet.data.remote_data_source.model.BreathingRateResponse
import com.example.mypet.data.remote_data_source.model.HeartRateResponse
import com.example.mypet.data.remote_data_source.model.PressureResponse
import com.example.mypet.data.remote_data_source.model.TemperatureResponse

interface Repository {
    suspend fun getTemperatureForDay(): TemperatureResponse

    suspend fun getTemperatureForWeek(): TemperatureResponse

    suspend fun getTemperatureForMonth(): TemperatureResponse

    suspend fun getPressureForDay(): PressureResponse

    suspend fun getPressureForWeek(): PressureResponse

    suspend fun getPressureForMonth(): PressureResponse

    suspend fun getHeartRateForDay(): HeartRateResponse

    suspend fun getHeartRateForWeek(): HeartRateResponse

    suspend fun getHeartRateForMonth(): HeartRateResponse

    suspend fun getBreathingRateForDay(): BreathingRateResponse

    suspend fun getBreathingRateForWeek(): BreathingRateResponse

    suspend fun getBreathingRateForMonth(): BreathingRateResponse
}