package com.example.mypet.domain.model.pet

data class Pet(
    val id: Int = 0,
    val ownerId : Int,
    val name: String,
    val age: Int
)