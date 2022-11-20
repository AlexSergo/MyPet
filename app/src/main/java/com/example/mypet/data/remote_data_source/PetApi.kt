package com.example.mypet.data.remote_data_source

import com.example.mypet.data.remote_data_source.model.parameters.ParametersResponse
import com.example.mypet.data.remote_data_source.model.pet.PetRegisterRequest
import com.example.mypet.data.remote_data_source.model.pet.PetResponse
import com.example.mypet.data.remote_data_source.model.pet.PetsResponse
import com.example.mypet.data.remote_data_source.model.user.UserLoginRequest
import com.example.mypet.data.remote_data_source.model.user.UserRegisterRequest
import com.example.mypet.data.remote_data_source.model.user.UserResponse
import retrofit2.http.*

interface PetApi {

    @POST("/api/Clients")
    suspend fun createUser(@Body user: UserRegisterRequest): UserResponse

    @POST("/api/{owner_id}/pets")
    suspend fun createPet(@Body pet: PetRegisterRequest): PetResponse

    @GET("/api/Clients/login/{username}/{password}")
    suspend fun loginUser(@Path("username") username: String, @Path("password") password: String): UserResponse

    @GET("/api/parameters/{pet_id}/{period}")
    suspend fun getParameters(@Path("pet_id") id: Int, @Path("period") period: String): ParametersResponse

    @GET("/api/clients/{owner_id}/pets")
    suspend fun getPets(@Path("owner_id") ownerId: Int): List<PetResponse>
}