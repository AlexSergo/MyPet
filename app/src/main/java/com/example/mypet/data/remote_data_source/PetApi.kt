package com.example.mypet.data.remote_data_source

import com.example.mypet.data.remote_data_source.model.*
import retrofit2.http.GET

interface PetApi {
    @GET("/api/indicators/now")
    suspend fun getTemperatureForDay(): TemperatureResponse

    @GET("/api/indicators/now")
    suspend fun getTemperatureForWeek(): TemperatureResponse

    @GET("/api/indicators/now")
    suspend fun getTemperatureForMonth(): TemperatureResponse

    @GET("/api/indicators/now")
    suspend fun getPressureForDay(): PressureResponse

    @GET("/api/indicators/now")
    suspend fun getPressureForWeek(): PressureResponse

    @GET("/api/indicators/now")
    suspend fun getPressureForMonth(): PressureResponse

    @GET("/api/indicators/now")
    suspend fun getHeartRateForDay(): HeartRateResponse

    @GET("/api/indicators/now")
    suspend fun getHeartRateForWeek(): HeartRateResponse

    @GET("/api/indicators/now")
    suspend fun getHeartRateForMonth(): HeartRateResponse

    @GET("/api/indicators/today")
    suspend fun getBreathingRateForDay(): BreathingRateResponse

    @GET("/api/indicators/today")
    suspend fun getBreathingRateForWeek(): BreathingRateResponse

    @GET("/api/indicators/today")
    suspend fun getBreathingRateForMonth(): BreathingRateResponse
}