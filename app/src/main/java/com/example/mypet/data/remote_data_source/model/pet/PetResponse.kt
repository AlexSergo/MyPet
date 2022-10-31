package com.example.mypet.data.remote_data_source.model.pet

data class PetResponse(
    val id: Int,
    val ownerId: Int,
    val name: String,
    val age: Int
)
