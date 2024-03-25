package igor.petrov.simpleweatherforecastapp.data.repository

import igor.petrov.simpleweatherforecastapp.data.RetrofitInstance
import igor.petrov.simpleweatherforecastapp.data.dto.ForecastDto
import retrofit2.Response
import javax.inject.Inject

class ForecastRepository @Inject constructor() {

    suspend fun get5DaysForecast(locationKey:String): Response<ForecastDto> {
        return RetrofitInstance.accuweatherApi.get5DaysForecast(locationKey)
    }

}

