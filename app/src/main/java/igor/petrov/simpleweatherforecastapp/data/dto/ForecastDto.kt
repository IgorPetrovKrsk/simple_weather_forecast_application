package igor.petrov.simpleweatherforecastapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import igor.petrov.simpleweatherforecastapp.entity.Forecast

@JsonClass(generateAdapter = true)

data class ForecastDto(
    @Json(name = "Headline") override val forecastHeadLine: ForecastHeadLineDto,
    @Json(name = "DailyForecasts") override val dailyForecastList: List<DailyForecastDto>? = null
):Forecast
