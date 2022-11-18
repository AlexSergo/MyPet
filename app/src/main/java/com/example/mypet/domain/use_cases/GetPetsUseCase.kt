package com.example.mypet.domain.use_cases

import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.pet.Pet

class GetPetsUseCase(private val repository: Repository) {

    suspend fun execute(ownerId: Int): List<Pet>{
        val pets = repository.getPets(ownerId)
        val result = mutableListOf<Pet>()

        for (pet in pets){
            result.add(Pet(id = pet.id, name = pet.name, age = pet.age, ownerId = pet.clientId))
        }
        return result
    }
}