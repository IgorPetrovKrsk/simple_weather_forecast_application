package igor.petrov.simpleweatherforecastapp.domain

import igor.petrov.simpleweatherforecastapp.data.dao.LocationDao
import igor.petrov.simpleweatherforecastapp.data.dto.LocationDto
import igor.petrov.simpleweatherforecastapp.data.repository.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val locationsListRepository: LocationRepository, private val locationDao: LocationDao) {

    suspend fun getLocations(): List<LocationDto> {
        try {
            val response = locationsListRepository.getLocations()
            if (response.code() != 200 || response.body()?.isEmpty() == true) {
                val responseDB = locationDao.getLocations()
                if (responseDB.isNotEmpty())
                    return responseDB
                else
                    throw Throwable(response.errorBody()?.toString())
            } else {
                val locationList = response.body()!!
                locationDao.upsertLocations(locationList)
                return locationList
            }
        } catch (e: Exception) {
            val responseDB = locationDao.getLocations()
            if (responseDB.isNotEmpty())
                return responseDB
            else
                throw e
        }

    }
}