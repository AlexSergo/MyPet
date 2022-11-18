package com.example.mypet.domain.use_cases

import com.example.mypet.domain.Period
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.parameters.*

class GetParametersUseCase(private val repository: Repository) {

    suspend fun execute(petId: Int, period: Period): List<Parameters>{
        val params = when(period) {
                Period.HOUR -> repository.getParameters(petId, "hour")
                Period.DAY -> repository.getParameters(petId, "day")
                Period.WEEK -> repository.getParameters(petId, "week")
                Period.MONTH -> repository.getParameters(petId, "month")
        }
        val result = mutableListOf<Parameters>()

        for (parameter in params.parametersArray){
            result.add(Parameters(
                breathingRate = BreathingRate(parameter.breathingRate.breathingRate),
                heartRate = HeartRate(parameter.heartRate.heartRate),
                pressure = Pressure(parameter.pressure.topPressure, parameter.pressure.lowerPressure),
                temperature = Temperature(parameter.temperature.temperature),
                date = parameter.date
            ))
        }
        return result
    }
}