package com.example.mypet.domain.use_cases

import com.example.mypet.data.remote_data_source.model.user.UserRegisterRequest
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.user.User
import com.example.mypet.domain.model.user.UserRegister

class RegisterUserUseCase(private val repository: Repository) {

    suspend fun execute(user: UserRegister): User {
        val response= repository.createUser(
            UserRegisterRequest(
                username = user.username,
                email = user.email,
                password = user.password,
                name = user.name))

        return User(response.id, response.email, response.name)
    }
}