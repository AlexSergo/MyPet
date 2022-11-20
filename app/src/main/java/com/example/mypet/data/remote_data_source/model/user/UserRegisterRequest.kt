package com.example.mypet.data.remote_data_source.model.user

data class UserRegisterRequest(
    val name: String,
    val username: String,
    val password: String,
    val email: String
)
