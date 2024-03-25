package igor.petrov.simpleweatherforecastapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import igor.petrov.simpleweatherforecastapp.entity.GeoPosition

@JsonClass(generateAdapter = true)
data class GeoPositionDto(
    @Json(name = "Latitude") override val latitude: Double,
    @Json(name = "Longitude") override val longitude: Double
):GeoPosition
