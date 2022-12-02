package com.example.mypet.domain

import com.example.mypet.data.remote_data_source.model.parameters.Parameters
import com.example.mypet.data.remote_data_source.model.parameters.ParametersResponse
import com.example.mypet.data.remote_data_source.model.pet.PetRegisterRequest
import com.example.mypet.data.remote_data_source.model.pet.PetResponse
import com.example.mypet.data.remote_data_source.model.pet.PetsResponse
import com.example.mypet.data.remote_data_source.model.user.UserLoginRequest
import com.example.mypet.data.remote_data_source.model.user.UserRegisterRequest
import com.example.mypet.data.remote_data_source.model.user.UserResponse

interface Repository {
    suspend fun createUser(user: UserRegisterRequest): UserResponse

    suspend fun createPet(pet: PetRegisterRequest): PetResponse

    suspend fun loginUser(username: String, password: String): UserResponse

    suspend fun getParameters(id: Int, period: String): List<Parameters>

    suspend fun getPets(ownerId: Int): List<PetResponse>
}