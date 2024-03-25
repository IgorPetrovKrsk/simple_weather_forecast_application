package igor.petrov.simpleweatherforecastapp.data.dto

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import igor.petrov.simpleweatherforecastapp.entity.DailyForecast

@JsonClass(generateAdapter = true)
@Entity(tableName = "DailyForecastDB", primaryKeys = ["key","date"])
data class DailyForecastDto(
    @Json(ignore = true) override var key: String = "",
    @Json(name = "Date") override val date: String,
    @Json(name = "Temperature")override val temperature: TemperatureDto,
): DailyForecast
