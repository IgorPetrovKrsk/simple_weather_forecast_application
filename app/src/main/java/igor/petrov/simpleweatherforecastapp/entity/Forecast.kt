package igor.petrov.simpleweatherforecastapp.entity

interface Forecast {
    val forecastHeadLine: ForecastHeadLine
    val dailyForecastList:List<DailyForecast>?
}