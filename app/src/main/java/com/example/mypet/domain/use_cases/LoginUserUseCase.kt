package com.example.mypet.domain.use_cases

import com.example.mypet.data.remote_data_source.model.user.UserLoginRequest
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.user.User
import com.example.mypet.domain.model.user.UserLogin

class LoginUserUseCase(private val repository: Repository) {

    suspend fun execute(user: UserLogin): User {
        val userResponse = repository.loginUser(user.email, user.password)
        return User(userResponse.id, userResponse.email, userResponse.name)
    }
}