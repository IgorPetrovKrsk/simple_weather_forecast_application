package igor.petrov.simpleweatherforecastapp.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import igor.petrov.simpleweatherforecastapp.data.dao.CachedCitiesDao
import igor.petrov.simpleweatherforecastapp.data.dao.DailyForecastDao
import igor.petrov.simpleweatherforecastapp.data.dao.LocationDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun locationDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }
    @Provides
    fun dailyForecastDao(appDatabase: AppDatabase): DailyForecastDao {
        return appDatabase.dailyForecastDao()
    }
    @Provides
    fun cachedCitiesDao(appDatabase:AppDatabase): CachedCitiesDao {
        return appDatabase.cachedCitiesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "simple_weather_app_database"
        ).fallbackToDestructiveMigration().build()
    }
}
