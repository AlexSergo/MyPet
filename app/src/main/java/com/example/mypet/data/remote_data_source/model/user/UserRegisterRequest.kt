package com.example.mypet.data.remote_data_source.model.user

data class UserRegisterRequest(
    val username: String,
    val email: String,
    val phone: String,
    val password: String
)
