package com.example.mypet.domain.use_cases

import com.example.mypet.data.remote_data_source.model.pet.PetRegisterRequest
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.pet.Pet

class RegisterPetUseCase(private val repository: Repository) {

    suspend fun execute(pet: Pet): Pet {
        val response = repository.createPet(
            PetRegisterRequest(name = pet.name, age = pet.age, ownerId = pet.ownerId))

        return Pet(
            id = response.id,
            ownerId = response.ownerId,
            name = response.name,
            age = response.age)
    }
}