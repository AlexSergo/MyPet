package com.example.mypet.domain.use_cases

import com.example.mypet.domain.Period
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.Temperature
import java.util.*

class GetTemperatureUseCase(private val repository: Repository) {

    suspend fun execute(period: Period): List<Temperature>{

        val result = mutableListOf<Temperature>()

        val temperatures = when(period){
            Period.DAY -> repository.getTemperatureForDay().temperature
            Period.WEEK -> repository.getTemperatureForWeek().temperature
            Period.MONTH -> repository.getTemperatureForMonth().temperature
        }
        for (temperature in temperatures)
            result.add(Temperature(temperature = temperature.temperature, date = temperature.date))

        return result
    }
}