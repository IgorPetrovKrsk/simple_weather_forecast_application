package igor.petrov.simpleweatherforecastapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import igor.petrov.simpleweatherforecastapp.entity.TemperatureData

@JsonClass(generateAdapter = true)
data class TemperatureDataDto(
    @Json(name = "Value") override val value: Double,
    @Json(name = "Unit") override val unit: String
): TemperatureData
