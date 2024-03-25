package igor.petrov.simpleweatherforecastapp.data

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import igor.petrov.simpleweatherforecastapp.data.dto.GeoPositionDto
import igor.petrov.simpleweatherforecastapp.data.dto.TemperatureDto

@OptIn(ExperimentalStdlibApi::class)
class Converters {

    @TypeConverter
    fun fromGeoPosition(geoPosition: GeoPositionDto):String {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<GeoPositionDto> = moshi.adapter<GeoPositionDto>()
        return jsonAdapter.toJson(geoPosition)
    }

    @TypeConverter
    fun toGeoPosition(value: String): GeoPositionDto?{
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<GeoPositionDto> = moshi.adapter<GeoPositionDto>()
        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromTemperature(temperature: TemperatureDto):String {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<TemperatureDto> = moshi.adapter<TemperatureDto>()
        return jsonAdapter.toJson(temperature)
    }

    @TypeConverter
    fun toTemperature(value: String): TemperatureDto?{
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<TemperatureDto> = moshi.adapter<TemperatureDto>()
        return jsonAdapter.fromJson(value)
    }
}