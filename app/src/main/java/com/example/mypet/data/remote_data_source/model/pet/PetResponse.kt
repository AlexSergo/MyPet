package com.example.mypet.data.remote_data_source.model.pet

import com.google.gson.annotations.SerializedName

data class PetResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("clientId")
    val clientId: Int
)
