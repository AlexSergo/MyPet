package com.example.mypet.domain.use_cases

import com.example.mypet.domain.Period
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.HeartRate
import com.example.mypet.domain.model.Pressure

class GetHeartRateUseCase(private val repository: Repository) {

    suspend fun execute(period: Period): List<HeartRate>{

        val result = mutableListOf<HeartRate>()

        val heartRates = when(period){
            Period.DAY -> repository.getHeartRateForDay().heartRate
            Period.WEEK -> repository.getHeartRateForWeek().heartRate
            Period.MONTH -> repository.getHeartRateForMonth().heartRate
        }
        for (heartRate in heartRates)
            result.add(HeartRate(heartRate = heartRate.heartRate, date = heartRate.date))

        return result
    }
}