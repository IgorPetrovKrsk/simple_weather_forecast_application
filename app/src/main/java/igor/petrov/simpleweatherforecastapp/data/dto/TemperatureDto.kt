package igor.petrov.simpleweatherforecastapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import igor.petrov.simpleweatherforecastapp.entity.Temperature

@JsonClass(generateAdapter = true)
data class TemperatureDto(
    @Json(name = "Minimum") override val minimumTemperatureData: TemperatureDataDto,
    @Json(name = "Maximum") override val maximumTemperatureData: TemperatureDataDto
): Temperature
