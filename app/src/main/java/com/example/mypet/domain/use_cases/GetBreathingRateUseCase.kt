package com.example.mypet.domain.use_cases

import com.example.mypet.domain.Period
import com.example.mypet.domain.Repository
import com.example.mypet.domain.model.BreathingRate
import com.example.mypet.domain.model.HeartRate

class GetBreathingRateUseCase(private val repository: Repository) {

    suspend fun execute(period: Period): List<BreathingRate>{

        val result = mutableListOf<BreathingRate>()

        val breathingRates = when(period){
            Period.DAY -> repository.getBreathingRateForDay().breathingRate
            Period.WEEK -> repository.getBreathingRateForWeek().breathingRate
            Period.MONTH -> repository.getBreathingRateForMonth().breathingRate
        }
        for (breathingRate in breathingRates)
            result.add(BreathingRate(breathingRate = breathingRate.breathingRateNow,
                date = breathingRate.date))

        return result
    }
}