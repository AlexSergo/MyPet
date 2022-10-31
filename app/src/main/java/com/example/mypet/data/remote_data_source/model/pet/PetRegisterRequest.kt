package com.example.mypet.data.remote_data_source.model.pet

data class PetRegisterRequest(
    val name: String,
    val age: Int,
    val ownerId: Int
)
