package igor.petrov.simpleweatherforecastapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import igor.petrov.simpleweatherforecastapp.data.dao.CachedCitiesDao
import igor.petrov.simpleweatherforecastapp.data.dao.DailyForecastDao
import igor.petrov.simpleweatherforecastapp.data.dao.LocationDao
import igor.petrov.simpleweatherforecastapp.data.dto.DailyForecastDto
import igor.petrov.simpleweatherforecastapp.data.dto.LocationDto

@Database(entities = [
    LocationDto::class,
    DailyForecastDto::class
], version = 2,
    exportSchema = false)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun dailyForecastDao(): DailyForecastDao
    abstract fun cachedCitiesDao(): CachedCitiesDao
}