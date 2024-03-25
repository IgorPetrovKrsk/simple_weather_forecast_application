package igor.petrov.simpleweatherforecastapp.domain

import igor.petrov.simpleweatherforecastapp.data.repository.ForecastRepository
import igor.petrov.simpleweatherforecastapp.data.dao.DailyForecastDao
import igor.petrov.simpleweatherforecastapp.data.dto.DailyForecastDto
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val forecastRepository: ForecastRepository, private val dailyForecastDao: DailyForecastDao) {

    suspend fun get5DaysForecast(locationKey: String): List<DailyForecastDto> {
        try {
            val response = forecastRepository.get5DaysForecast(locationKey)
            if (response.code() != 200 || response.body() == null) {
                val dailyForecastList = dailyForecastDao.getDailyForecast(locationKey)
                if (dailyForecastList.isNotEmpty())
                    return dailyForecastList
                else
                    throw Throwable(response.errorBody()?.toString())
            } else {
                val dailyForecastList = response.body()!!.dailyForecastList!!
                dailyForecastList.forEach { it.key = locationKey } //updating key to store in DB
                dailyForecastDao.upsertDailyForecast(dailyForecastList)
                return dailyForecastList
            }
        } catch (e: Exception) {
            val dailyForecastList = dailyForecastDao.getDailyForecast(locationKey)
            if (dailyForecastList.isEmpty())
                throw e
            else
                return dailyForecastList
        }

    }
}