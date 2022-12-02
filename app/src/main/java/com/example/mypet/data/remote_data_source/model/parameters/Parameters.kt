package com.example.mypet.data.remote_data_source.model.parameters

import com.google.gson.annotations.SerializedName

data class Parameters(
    val id: Int,
    val heartRate: Int,
    val breathingRate: Int,
    val pressure: Pressure,
    val temperature: Float,
    val muscleActivity: Int,
    @SerializedName("createdDateTime")
    val date: String,
    val petId: Int
)
