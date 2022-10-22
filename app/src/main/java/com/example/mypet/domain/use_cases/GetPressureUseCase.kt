package com.example.mypet.domain.use_cases

import com.example.mypet.domain.Period
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.Pressure

class GetPressureUseCase(private val repository: Repository) {

    suspend fun execute(period: Period): List<Pressure>{

        val result = mutableListOf<Pressure>()

        val pressures = when(period){
            Period.DAY -> repository.getPressureForDay().pressure
            Period.WEEK -> repository.getPressureForWeek().pressure
            Period.MONTH -> repository.getPressureForMonth().pressure
        }
        for (pressure in pressures)
            result.add(Pressure(topPressure = pressure.topPressure,
                lowerPressure = pressure.lowerPressure, date = pressure.date))

        return result
    }
}