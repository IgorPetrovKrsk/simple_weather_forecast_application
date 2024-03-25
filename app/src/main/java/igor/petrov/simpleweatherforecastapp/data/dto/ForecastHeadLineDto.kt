package igor.petrov.simpleweatherforecastapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import igor.petrov.simpleweatherforecastapp.entity.ForecastHeadLine

@JsonClass(generateAdapter = true)
data class ForecastHeadLineDto(
    @Json(name = "EffectiveDate") override val effectiveDate: String,
    @Json(name = "MobileLink") override val mobileLink: String?,
    @Json(name = "Link") override val link: String?
):ForecastHeadLine
