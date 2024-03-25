package igor.petrov.simpleweatherforecastapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import igor.petrov.simpleweatherforecastapp.data.dto.LocationDto

@Dao
interface CachedCitiesDao {
    @Query("Select DISTINCT LocationsDB.`key`, LocationsDB.type, LocationsDB.localizedName,LocationsDB.englishName,LocationsDB.geoPosition FROM LocationsDB JOIN DailyForecastDB ON DailyForecastDB.`key` = LocationsDB.`key`")
    fun getCachedCities(): List<LocationDto>
}
