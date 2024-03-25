package igor.petrov.simpleweatherforecastapp.domain

import igor.petrov.simpleweatherforecastapp.data.dao.CachedCitiesDao
import igor.petrov.simpleweatherforecastapp.data.dto.LocationDto
import javax.inject.Inject

class GetCachedCitiesUseCase @Inject constructor(private val cachedCitiesDao: CachedCitiesDao) {

    suspend fun gatCachedCities(): List<LocationDto> {
        return cachedCitiesDao.getCachedCities()
    }
}