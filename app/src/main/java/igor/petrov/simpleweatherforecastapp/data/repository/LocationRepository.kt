package igor.petrov.simpleweatherforecastapp.data.repository

import igor.petrov.simpleweatherforecastapp.data.RetrofitInstance
import igor.petrov.simpleweatherforecastapp.data.dto.LocationDto
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor() {

    suspend fun getLocations(): Response<List<LocationDto>> {
        return RetrofitInstance.accuweatherApi.getTopCities()
    }

}

