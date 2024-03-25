package igor.petrov.simpleweatherforecastapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import igor.petrov.simpleweatherforecastapp.data.dto.DailyForecastDto

@Dao
interface DailyForecastDao {

    @Query("Select * FROM dailyforecastdb WHERE `key` = :locationKey")
    fun getDailyForecast(locationKey:String): List<DailyForecastDto>

    @Upsert
    fun upsertDailyForecast(dailyForecastList: List<DailyForecastDto>)
}