package com.example.mypet.domain.model.parameters

data class Parameters(
    val breathingRate: BreathingRate,
    val heartRate: HeartRate,
    val pressure: Pressure,
    val temperature: Temperature,
    val muscleActivity: MuscleActivity,
    val date: String
)