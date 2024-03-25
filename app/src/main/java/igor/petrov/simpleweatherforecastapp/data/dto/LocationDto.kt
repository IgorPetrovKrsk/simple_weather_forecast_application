package igor.petrov.simpleweatherforecastapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import igor.petrov.simpleweatherforecastapp.entity.Location

@JsonClass(generateAdapter = true)
@Entity(tableName = "LocationsDB")
data class LocationDto(
    @PrimaryKey
    @ColumnInfo(name = "key")
    @Json(name = "Key") override val key: String,
    @ColumnInfo(name = "type")
    @Json(name = "Type") override val type: String,
    @ColumnInfo(name = "localizedName")
    @Json(name = "LocalizedName") override val localizedName: String? = null,
    @ColumnInfo(name = "englishName")
    @Json(name = "EnglishName") override val englishName: String?=null,
    @ColumnInfo(name = "geoPosition")
    @Json(name = "GeoPosition") override val geoPosition: GeoPositionDto?=null
):Location
