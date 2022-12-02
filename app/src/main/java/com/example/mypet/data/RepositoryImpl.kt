package com.example.mypet.data

import com.example.mypet.data.local_data_source.PetDao
import com.example.mypet.data.remote_data_source.PetApi
import com.example.mypet.data.remote_data_source.model.parameters.Parameters
import com.example.mypet.data.remote_data_source.model.parameters.ParametersResponse
import com.example.mypet.data.remote_data_source.model.pet.PetRegisterRequest
import com.example.mypet.data.remote_data_source.model.pet.PetResponse
import com.example.mypet.data.remote_data_source.model.pet.PetsResponse
import com.example.mypet.data.remote_data_source.model.user.UserLoginRequest
import com.example.mypet.data.remote_data_source.model.user.UserRegisterRequest
import com.example.mypet.data.remote_data_source.model.user.UserResponse
import com.example.mypet.domain.Repository

class RepositoryImpl(private val api: PetApi): Repository {

    override suspend fun createUser(user: UserRegisterRequest): UserResponse {
        return api.createUser(user)
    }

    override suspend fun createPet(pet: PetRegisterRequest): PetResponse {
        return api.createPet(pet)
    }

    override suspend fun loginUser(username: String, password: String): UserResponse {
        return api.loginUser(username, password)
    }

    override suspend fun getParameters(id: Int, period: String): List<Parameters> {
        return api.getParameters(id, period)
    }

    override suspend fun getPets(ownerId: Int): List<PetResponse> {
        return api.getPets(ownerId)
    }


}