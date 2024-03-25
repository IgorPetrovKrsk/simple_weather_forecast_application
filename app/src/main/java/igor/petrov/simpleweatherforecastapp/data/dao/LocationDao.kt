package igor.petrov.simpleweatherforecastapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import igor.petrov.simpleweatherforecastapp.data.dto.LocationDto

@Dao
interface LocationDao {

    @Query("Select * FROM LocationsDB")
    fun getLocations(): List<LocationDto>

    @Upsert
    fun upsertLocations(locationsList: List<LocationDto>)
}