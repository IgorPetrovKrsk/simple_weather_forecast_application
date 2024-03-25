package igor.petrov.simpleweatherforecastapp.data

import igor.petrov.simpleweatherforecastapp.data.dto.ForecastDto
import igor.petrov.simpleweatherforecastapp.data.dto.LocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccuweatherApi {
    @GET("locations/v1/topcities/150")
    suspend fun getTopCities(
        @Query("apikey") apiKey: String = accuweather_api_key
    ): Response<List<LocationDto>>

    @GET("forecasts/v1/daily/5day/{locationKey}")
    suspend fun get5DaysForecast(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String = accuweather_api_key,
        @Query("metric") metric: Boolean = true
    ): Response<ForecastDto>

    companion object {
        private const val accuweather_api_key = "VkJS629vDSGjGCdWRxpgkTi9LBrVeHFs"
        //private const val accuweather_api_key = "tEIPhzoWJ7EMCGOs0JqMAQHFTdPOHizo"
    }
}

