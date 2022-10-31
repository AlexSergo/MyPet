package com.example.mypet.domain.model.user

data class UserRegister(
    val username: String,
    val email: String,
    val phone: String,
    val password: String
)